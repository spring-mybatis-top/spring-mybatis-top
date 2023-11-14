/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import InfoDetailComponent from '@/entities/info/info-details.vue';
import InfoClass from '@/entities/info/info-details.component';
import InfoService from '@/entities/info/info.service';
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
  describe('Info Management Detail Component', () => {
    let wrapper: Wrapper<InfoClass>;
    let comp: InfoClass;
    let infoServiceStub: SinonStubbedInstance<InfoService>;

    beforeEach(() => {
      infoServiceStub = sinon.createStubInstance<InfoService>(InfoService);

      wrapper = shallowMount<InfoClass>(InfoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { infoService: () => infoServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInfo = { id: 123 };
        infoServiceStub.find.resolves(foundInfo);

        // WHEN
        comp.retrieveInfo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.info).toBe(foundInfo);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundInfo = { id: 123 };
        infoServiceStub.find.resolves(foundInfo);

        // WHEN
        comp.beforeRouteEnter({ params: { infoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.info).toBe(foundInfo);
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
