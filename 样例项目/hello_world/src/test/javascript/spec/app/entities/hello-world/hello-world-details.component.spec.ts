/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import HelloWorldDetailComponent from '@/entities/hello-world/hello-world-details.vue';
import HelloWorldClass from '@/entities/hello-world/hello-world-details.component';
import HelloWorldService from '@/entities/hello-world/hello-world.service';
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
  describe('HelloWorld Management Detail Component', () => {
    let wrapper: Wrapper<HelloWorldClass>;
    let comp: HelloWorldClass;
    let helloWorldServiceStub: SinonStubbedInstance<HelloWorldService>;

    beforeEach(() => {
      helloWorldServiceStub = sinon.createStubInstance<HelloWorldService>(HelloWorldService);

      wrapper = shallowMount<HelloWorldClass>(HelloWorldDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { helloWorldService: () => helloWorldServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundHelloWorld = { id: 123 };
        helloWorldServiceStub.find.resolves(foundHelloWorld);

        // WHEN
        comp.retrieveHelloWorld(123);
        await comp.$nextTick();

        // THEN
        expect(comp.helloWorld).toBe(foundHelloWorld);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundHelloWorld = { id: 123 };
        helloWorldServiceStub.find.resolves(foundHelloWorld);

        // WHEN
        comp.beforeRouteEnter({ params: { helloWorldId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.helloWorld).toBe(foundHelloWorld);
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
