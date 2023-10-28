import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInfo } from '@/shared/model/info.model';
import InfoService from './info.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class InfoDetails extends Vue {
  @Inject('infoService') private infoService: () => InfoService;
  @Inject('alertService') private alertService: () => AlertService;

  public info: IInfo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.infoId) {
        vm.retrieveInfo(to.params.infoId);
      }
    });
  }

  public retrieveInfo(infoId) {
    this.infoService()
      .find(infoId)
      .then(res => {
        this.info = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
