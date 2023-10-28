/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import InfoUpdateComponent from '@/entities/info/info-update.vue';
import InfoClass from '@/entities/info/info-update.component';
import InfoService from '@/entities/info/info.service';

import PersonService from '@/entities/person/person.service';
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
  describe('Info Management Update Component', () => {
    let wrapper: Wrapper<InfoClass>;
    let comp: InfoClass;
    let infoServiceStub: SinonStubbedInstance<InfoService>;

    beforeEach(() => {
      infoServiceStub = sinon.createStubInstance<InfoService>(InfoService);

      wrapper = shallowMount<InfoClass>(InfoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          infoService: () => infoServiceStub,
          alertService: () => new AlertService(),

          personService: () =>
            sinon.createStubInstance<PersonService>(PersonService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.info = entity;
        infoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(infoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.info = entity;
        infoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(infoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundInfo = { id: 123 };
        infoServiceStub.find.resolves(foundInfo);
        infoServiceStub.retrieve.resolves([foundInfo]);

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
