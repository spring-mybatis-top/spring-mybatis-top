/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import HelloWorldUpdateComponent from '@/entities/hello-world/hello-world-update.vue';
import HelloWorldClass from '@/entities/hello-world/hello-world-update.component';
import HelloWorldService from '@/entities/hello-world/hello-world.service';

import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('HelloWorld Management Update Component', () => {
    let wrapper: Wrapper<HelloWorldClass>;
    let comp: HelloWorldClass;
    let helloWorldServiceStub: SinonStubbedInstance<HelloWorldService>;

    beforeEach(() => {
      helloWorldServiceStub = sinon.createStubInstance<HelloWorldService>(HelloWorldService);

      wrapper = shallowMount<HelloWorldClass>(HelloWorldUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          helloWorldService: () => helloWorldServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.helloWorld = entity;
        helloWorldServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(helloWorldServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.helloWorld = entity;
        helloWorldServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(helloWorldServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundHelloWorld = { id: 123 };
        helloWorldServiceStub.find.resolves(foundHelloWorld);
        helloWorldServiceStub.retrieve.resolves([foundHelloWorld]);

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
