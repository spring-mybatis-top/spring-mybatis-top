import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const AllField = () => import('@/entities/all-field/all-field.vue');
const AllFieldUpdate = () => import('@/entities/all-field/all-field-update.vue');
const AllFieldDetails = () => import('@/entities/all-field/all-field-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'all-field',
      name: 'AllField',
      component: AllField,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'all-field/new',
      name: 'AllFieldCreate',
      component: AllFieldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'all-field/:allFieldId/edit',
      name: 'AllFieldEdit',
      component: AllFieldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'all-field/:allFieldId/view',
      name: 'AllFieldView',
      component: AllFieldDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
