import { Component, Vue, Inject } from 'vue-property-decorator';

import { IHelloWorld } from '@/shared/model/hello-world.model';
import HelloWorldService from './hello-world.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class HelloWorldDetails extends Vue {
  @Inject('helloWorldService') private helloWorldService: () => HelloWorldService;
  @Inject('alertService') private alertService: () => AlertService;

  public helloWorld: IHelloWorld = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.helloWorldId) {
        vm.retrieveHelloWorld(to.params.helloWorldId);
      }
    });
  }

  public retrieveHelloWorld(helloWorldId) {
    this.helloWorldService()
      .find(helloWorldId)
      .then(res => {
        this.helloWorld = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
