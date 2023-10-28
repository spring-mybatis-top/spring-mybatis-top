import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IHelloWorld, HelloWorld } from '@/shared/model/hello-world.model';
import HelloWorldService from './hello-world.service';

const validations: any = {
  helloWorld: {
    name: {},
    age: {},
  },
};

@Component({
  validations,
})
export default class HelloWorldUpdate extends Vue {
  @Inject('helloWorldService') private helloWorldService: () => HelloWorldService;
  @Inject('alertService') private alertService: () => AlertService;

  public helloWorld: IHelloWorld = new HelloWorld();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.helloWorldId) {
        vm.retrieveHelloWorld(to.params.helloWorldId);
      }
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
    if (this.helloWorld.id) {
      this.helloWorldService()
        .update(this.helloWorld)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('helloWorldApp.helloWorld.updated', { param: param.id });
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
      this.helloWorldService()
        .create(this.helloWorld)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('helloWorldApp.helloWorld.created', { param: param.id });
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

  public retrieveHelloWorld(helloWorldId): void {
    this.helloWorldService()
      .find(helloWorldId)
      .then(res => {
        this.helloWorld = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
