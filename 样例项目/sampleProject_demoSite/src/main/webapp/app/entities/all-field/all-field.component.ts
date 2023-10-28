import { mixins } from 'vue-class-component';
import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAllField } from '@/shared/model/all-field.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import AllFieldService from './all-field.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class AllField extends mixins(JhiDataUtils) {
  @Inject('allFieldService') private allFieldService: () => AllFieldService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public allFields: IAllField[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAllFields();
  }

  public clear(): void {
    this.retrieveAllAllFields();
  }

  public retrieveAllAllFields(): void {
    this.isFetching = true;
    this.allFieldService()
      .retrieve()
      .then(
        res => {
          this.allFields = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IAllField): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAllField(): void {
    this.allFieldService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sampleProjectAllFildApp.allField.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllAllFields();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
