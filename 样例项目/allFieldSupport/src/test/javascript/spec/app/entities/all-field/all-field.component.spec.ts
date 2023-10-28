/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import AllFieldComponent from '@/entities/all-field/all-field.vue';
import AllFieldClass from '@/entities/all-field/all-field.component';
import AllFieldService from '@/entities/all-field/all-field.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('AllField Management Component', () => {
    let wrapper: Wrapper<AllFieldClass>;
    let comp: AllFieldClass;
    let allFieldServiceStub: SinonStubbedInstance<AllFieldService>;

    beforeEach(() => {
      allFieldServiceStub = sinon.createStubInstance<AllFieldService>(AllFieldService);
      allFieldServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AllFieldClass>(AllFieldComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          allFieldService: () => allFieldServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      allFieldServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAllFields();
      await comp.$nextTick();

      // THEN
      expect(allFieldServiceStub.retrieve.called).toBeTruthy();
      expect(comp.allFields[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      allFieldServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(allFieldServiceStub.retrieve.callCount).toEqual(1);

      comp.removeAllField();
      await comp.$nextTick();

      // THEN
      expect(allFieldServiceStub.delete.called).toBeTruthy();
      expect(allFieldServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
