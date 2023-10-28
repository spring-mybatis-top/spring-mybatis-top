/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AllFieldDetailComponent from '@/entities/all-field/all-field-details.vue';
import AllFieldClass from '@/entities/all-field/all-field-details.component';
import AllFieldService from '@/entities/all-field/all-field.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('AllField Management Detail Component', () => {
    let wrapper: Wrapper<AllFieldClass>;
    let comp: AllFieldClass;
    let allFieldServiceStub: SinonStubbedInstance<AllFieldService>;

    beforeEach(() => {
      allFieldServiceStub = sinon.createStubInstance<AllFieldService>(AllFieldService);

      wrapper = shallowMount<AllFieldClass>(AllFieldDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { allFieldService: () => allFieldServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAllField = { id: 123 };
        allFieldServiceStub.find.resolves(foundAllField);

        // WHEN
        comp.retrieveAllField(123);
        await comp.$nextTick();

        // THEN
        expect(comp.allField).toBe(foundAllField);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAllField = { id: 123 };
        allFieldServiceStub.find.resolves(foundAllField);

        // WHEN
        comp.beforeRouteEnter({ params: { allFieldId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.allField).toBe(foundAllField);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
