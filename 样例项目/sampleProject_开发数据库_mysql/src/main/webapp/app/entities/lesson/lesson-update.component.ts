import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import StudentService from '@/entities/student/student.service';
import { IStudent } from '@/shared/model/student.model';

import { ILesson, Lesson } from '@/shared/model/lesson.model';
import LessonService from './lesson.service';

const validations: any = {
  lesson: {
    lesnName: {
      required,
    },
    teacherName: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class LessonUpdate extends Vue {
  @Inject('lessonService') private lessonService: () => LessonService;
  @Inject('alertService') private alertService: () => AlertService;

  public lesson: ILesson = new Lesson();

  @Inject('studentService') private studentService: () => StudentService;

  public students: IStudent[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lessonId) {
        vm.retrieveLesson(to.params.lessonId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.lesson.id) {
      this.lessonService()
        .update(this.lesson)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sampleProjectMysqlApp.lesson.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.lessonService()
        .create(this.lesson)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sampleProjectMysqlApp.lesson.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveLesson(lessonId): void {
    this.lessonService()
      .find(lessonId)
      .then(res => {
        this.lesson = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.studentService()
      .retrieve()
      .then(res => {
        this.students = res.data;
      });
  }
}
