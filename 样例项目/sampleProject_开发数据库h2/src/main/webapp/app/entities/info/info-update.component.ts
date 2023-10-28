import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import PersonService from '@/entities/person/person.service';
import { IPerson } from '@/shared/model/person.model';

import { IInfo, Info } from '@/shared/model/info.model';
import InfoService from './info.service';

const validations: any = {
  info: {
    comments: {
      required,
    },
    image: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class InfoUpdate extends Vue {
  @Inject('infoService') private infoService: () => InfoService;
  @Inject('alertService') private alertService: () => AlertService;

  public info: IInfo = new Info();

  @Inject('personService') private personService: () => PersonService;

  public people: IPerson[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.infoId) {
        vm.retrieveInfo(to.params.infoId);
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
    if (this.info.id) {
      this.infoService()
        .update(this.info)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('helloWorldApp.info.updated', { param: param.id });
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
      this.infoService()
        .create(this.info)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('helloWorldApp.info.created', { param: param.id });
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

  public retrieveInfo(infoId): void {
    this.infoService()
      .find(infoId)
      .then(res => {
        this.info = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.personService()
      .retrieve()
      .then(res => {
        this.people = res.data;
      });
  }
}
