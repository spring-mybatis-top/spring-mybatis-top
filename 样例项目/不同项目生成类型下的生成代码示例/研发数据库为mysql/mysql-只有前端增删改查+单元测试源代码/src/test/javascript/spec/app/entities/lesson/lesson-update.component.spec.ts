/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LessonUpdateComponent from '@/entities/lesson/lesson-update.vue';
import LessonClass from '@/entities/lesson/lesson-update.component';
import LessonService from '@/entities/lesson/lesson.service';

import StudentService from '@/entities/student/student.service';
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
  describe('Lesson Management Update Component', () => {
    let wrapper: Wrapper<LessonClass>;
    let comp: LessonClass;
    let lessonServiceStub: SinonStubbedInstance<LessonService>;

    beforeEach(() => {
      lessonServiceStub = sinon.createStubInstance<LessonService>(LessonService);

      wrapper = shallowMount<LessonClass>(LessonUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          lessonService: () => lessonServiceStub,
          alertService: () => new AlertService(),

          studentService: () =>
            sinon.createStubInstance<StudentService>(StudentService, {
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
        comp.lesson = entity;
        lessonServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(lessonServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.lesson = entity;
        lessonServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(lessonServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
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
