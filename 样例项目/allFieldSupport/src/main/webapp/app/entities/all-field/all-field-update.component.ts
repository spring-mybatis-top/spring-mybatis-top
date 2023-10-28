import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required, numeric, minValue, maxValue, decimal } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IAllField, AllField } from '@/shared/model/all-field.model';
import AllFieldService from './all-field.service';

const validations: any = {
  allField: {
    allLowercaseRegex: {
      required,
    },
    nameInteger: {
      required,
      numeric,
      min: minValue(10),
      max: maxValue(100),
    },
    nameLong: {
      required,
      numeric,
      min: minValue(10),
      max: maxValue(100),
    },
    nameBigDecimal: {
      required,
      decimal,
      min: minValue(10),
      max: maxValue(100),
    },
    nameFloat: {
      required,
      decimal,
      min: minValue(10),
      max: maxValue(100),
    },
    nameDouble: {
      required,
      decimal,
      min: minValue(10),
      max: maxValue(100),
    },
    nameBoolean: {
      required,
    },
    nameLocalDate: {
      required,
    },
    nameZonedDateTime: {
      required,
    },
    nameInstant: {
      required,
    },
    nameBlob: {
      required,
    },
    nameAnyBlob: {
      required,
    },
    nameImageBlob: {
      required,
    },
    nameTextBlob: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class AllFieldUpdate extends mixins(JhiDataUtils) {
  @Inject('allFieldService') private allFieldService: () => AllFieldService;
  @Inject('alertService') private alertService: () => AlertService;

  public allField: IAllField = new AllField();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.allFieldId) {
        vm.retrieveAllField(to.params.allFieldId);
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
    if (this.allField.id) {
      this.allFieldService()
        .update(this.allField)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('allFieldSupportApp.allField.updated', { param: param.id });
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
      this.allFieldService()
        .create(this.allField)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('allFieldSupportApp.allField.created', { param: param.id });
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.allField[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.allField[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.allField[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.allField[field] = null;
    }
  }

  public retrieveAllField(allFieldId): void {
    this.allFieldService()
      .find(allFieldId)
      .then(res => {
        res.nameZonedDateTime = new Date(res.nameZonedDateTime);
        res.nameInstant = new Date(res.nameInstant);
        this.allField = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.allField && field && fieldContentType) {
      if (Object.prototype.hasOwnProperty.call(this.allField, field)) {
        this.allField[field] = null;
      }
      if (Object.prototype.hasOwnProperty.call(this.allField, fieldContentType)) {
        this.allField[fieldContentType] = null;
      }
      if (idInput) {
        (<any>this).$refs[idInput] = null;
      }
    }
  }

  public initRelationships(): void {}
}
