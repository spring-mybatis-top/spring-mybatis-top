<template>
  <div>
    <h2 id="page-heading" data-cy="PersonHeading">
      <span v-text="$t('sampleProjectH2DiskApp.person.home.title')" id="person-heading">People</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sampleProjectH2DiskApp.person.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'PersonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-person"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sampleProjectH2DiskApp.person.home.createLabel')"> Create a new Person </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && people && people.length === 0">
      <span v-text="$t('sampleProjectH2DiskApp.person.home.notFound')">No people found</span>
    </div>
    <div class="table-responsive" v-if="people && people.length > 0">
      <table class="table table-striped" aria-describedby="people">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('personName')">
              <span v-text="$t('sampleProjectH2DiskApp.person.personName')">Person Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'personName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('age')">
              <span v-text="$t('sampleProjectH2DiskApp.person.age')">Age</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'age'"></jhi-sort-indicator>
            </th>
            <th scope="row"><span v-text="$t('sampleProjectH2DiskApp.person.myinfo')">Myinfo</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="person in people" :key="person.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PersonView', params: { personId: person.id } }">{{ person.id }}</router-link>
            </td>
            <td>{{ person.personName }}</td>
            <td>{{ person.age }}</td>
            <td>
              <div v-if="person.myinfo">
                <router-link :to="{ name: 'InfoView', params: { infoId: person.myinfo.id } }">{{ person.myinfo.comments }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PersonView', params: { personId: person.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PersonEdit', params: { personId: person.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(person)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="sampleProjectH2DiskApp.person.delete.question" data-cy="personDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-person-heading" v-text="$t('sampleProjectH2DiskApp.person.delete.question', { id: removeId })">
          Are you sure you want to delete this Person?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-person"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removePerson()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="people && people.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./person.component.ts"></script>
