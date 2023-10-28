import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, numeric, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import LessonService from '@/entities/lesson/lesson.service';
import { ILesson } from '@/shared/model/lesson.model';

import { IStudent, Student } from '@/shared/model/student.model';
import StudentService from './student.service';
import { Language } from '@/shared/model/enumerations/language.model';

const validations: any = {
  student: {
    stuName: {
      required,
    },
    money: {
      numeric,
      min: minValue(1),
      max: maxValue(100000),
    },
    language: {},
  },
};

@Component({
  validations,
})
export default class StudentUpdate extends Vue {
  @Inject('studentService') private studentService: () => StudentService;
  @Inject('alertService') private alertService: () => AlertService;

  public student: IStudent = new Student();

  @Inject('lessonService') private lessonService: () => LessonService;

  public lessons: ILesson[] = [];
  public languageValues: string[] = Object.keys(Language);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.studentId) {
        vm.retrieveStudent(to.params.studentId);
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
    this.student.mylesns = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.student.id) {
      this.studentService()
        .update(this.student)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sampleProjectAllFildApp.student.updated', { param: param.id });
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
      this.studentService()
        .create(this.student)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sampleProjectAllFildApp.student.created', { param: param.id });
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

  public retrieveStudent(studentId): void {
    this.studentService()
      .find(studentId)
      .then(res => {
        this.student = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.lessonService()
      .retrieve_filter('all')
      .then(res => {
        this.lessons = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      return selectedVals.find(value => option.id === value.id) ?? option;
    }
    return option;
  }
}
