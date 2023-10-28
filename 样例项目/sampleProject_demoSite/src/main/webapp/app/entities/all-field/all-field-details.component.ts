import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IAllField } from '@/shared/model/all-field.model';
import AllFieldService from './all-field.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class AllFieldDetails extends mixins(JhiDataUtils) {
  @Inject('allFieldService') private allFieldService: () => AllFieldService;
  @Inject('alertService') private alertService: () => AlertService;

  public allField: IAllField = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.allFieldId) {
        vm.retrieveAllField(to.params.allFieldId);
      }
    });
  }

  public retrieveAllField(allFieldId) {
    this.allFieldService()
      .find(allFieldId)
      .then(res => {
        this.allField = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
