import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, numeric, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import InfoService from '@/entities/info/info.service';
import { IInfo } from '@/shared/model/info.model';

import { IPerson, Person } from '@/shared/model/person.model';
import PersonService from './person.service';

const validations: any = {
  person: {
    personName: {
      required,
    },
    age: {
      numeric,
      min: minValue(1),
      max: maxValue(200),
    },
  },
};

@Component({
  validations,
})
export default class PersonUpdate extends Vue {
  @Inject('personService') private personService: () => PersonService;
  @Inject('alertService') private alertService: () => AlertService;

  public person: IPerson = new Person();

  @Inject('infoService') private infoService: () => InfoService;

  public infos: IInfo[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.personId) {
        vm.retrievePerson(to.params.personId);
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
    if (this.person.id) {
      this.personService()
        .update(this.person)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sampleProjectMysqlApp.person.updated', { param: param.id });
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
      this.personService()
        .create(this.person)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sampleProjectMysqlApp.person.created', { param: param.id });
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

  public retrievePerson(personId): void {
    this.personService()
      .find(personId)
      .then(res => {
        this.person = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.infoService()
      .retrieve_filter('myperson-is-null')
      .then(res => {
        this.infos = res.data;
        if (this.person.myinfo != null) this.infos.push(this.person.myinfo);
      });
  }
}
