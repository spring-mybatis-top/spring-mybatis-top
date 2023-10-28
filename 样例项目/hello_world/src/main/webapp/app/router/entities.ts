import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const HelloWorld = () => import('@/entities/hello-world/hello-world.vue');
const HelloWorldUpdate = () => import('@/entities/hello-world/hello-world-update.vue');
const HelloWorldDetails = () => import('@/entities/hello-world/hello-world-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'hello-world',
      name: 'HelloWorld',
      component: HelloWorld,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'hello-world/new',
      name: 'HelloWorldCreate',
      component: HelloWorldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'hello-world/:helloWorldId/edit',
      name: 'HelloWorldEdit',
      component: HelloWorldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'hello-world/:helloWorldId/view',
      name: 'HelloWorldView',
      component: HelloWorldDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
