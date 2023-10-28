import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILesson } from '@/shared/model/lesson.model';
import LessonService from './lesson.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LessonDetails extends Vue {
  @Inject('lessonService') private lessonService: () => LessonService;
  @Inject('alertService') private alertService: () => AlertService;

  public lesson: ILesson = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lessonId) {
        vm.retrieveLesson(to.params.lessonId);
      }
    });
  }

  public retrieveLesson(lessonId) {
    this.lessonService()
      .find(lessonId)
      .then(res => {
        this.lesson = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
