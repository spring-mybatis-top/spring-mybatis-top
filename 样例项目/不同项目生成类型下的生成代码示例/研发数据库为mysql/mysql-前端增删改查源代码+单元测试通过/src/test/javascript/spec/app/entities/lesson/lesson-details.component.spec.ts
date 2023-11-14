/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LessonDetailComponent from '@/entities/lesson/lesson-details.vue';
import LessonClass from '@/entities/lesson/lesson-details.component';
import LessonService from '@/entities/lesson/lesson.service';
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
  describe('Lesson Management Detail Component', () => {
    let wrapper: Wrapper<LessonClass>;
    let comp: LessonClass;
    let lessonServiceStub: SinonStubbedInstance<LessonService>;

    beforeEach(() => {
      lessonServiceStub = sinon.createStubInstance<LessonService>(LessonService);

      wrapper = shallowMount<LessonClass>(LessonDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { lessonService: () => lessonServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLesson = { id: 123 };
        lessonServiceStub.find.resolves(foundLesson);

        // WHEN
        comp.retrieveLesson(123);
        await comp.$nextTick();

        // THEN
        expect(comp.lesson).toBe(foundLesson);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLesson = { id: 123 };
        lessonServiceStub.find.resolves(foundLesson);

        // WHEN
        comp.beforeRouteEnter({ params: { lessonId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.lesson).toBe(foundLesson);
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
