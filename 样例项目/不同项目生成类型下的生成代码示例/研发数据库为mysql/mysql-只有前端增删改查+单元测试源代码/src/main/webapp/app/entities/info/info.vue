<template>
  <div>
    <h2 id="page-heading" data-cy="InfoHeading">
      <span v-text="$t('sampleProjectMysqlApp.info.home.title')" id="info-heading">Infos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sampleProjectMysqlApp.info.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'InfoCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-info">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sampleProjectMysqlApp.info.home.createLabel')"> Create a new Info </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && infos && infos.length === 0">
      <span v-text="$t('sampleProjectMysqlApp.info.home.notFound')">No infos found</span>
    </div>
    <div class="table-responsive" v-if="infos && infos.length > 0">
      <table class="table table-striped" aria-describedby="infos">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('comments')">
              <span v-text="$t('sampleProjectMysqlApp.info.comments')">Comments</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'comments'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('image')">
              <span v-text="$t('sampleProjectMysqlApp.info.image')">Image</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'image'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="info in infos" :key="info.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'InfoView', params: { infoId: info.id } }">{{ info.id }}</router-link>
            </td>
            <td>{{ info.comments }}</td>
            <td>{{ info.image }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'InfoView', params: { infoId: info.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'InfoEdit', params: { infoId: info.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(info)"
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
        <infinite-loading
          ref="infiniteLoading"
          v-if="totalItems > itemsPerPage"
          :identifier="infiniteId"
          slot="append"
          @infinite="loadMore"
          force-use-infinite-wrapper=".el-table__body-wrapper"
          :distance="20"
        >
        </infinite-loading>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="sampleProjectMysqlApp.info.delete.question" data-cy="infoDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-info-heading" v-text="$t('sampleProjectMysqlApp.info.delete.question', { id: removeId })">
          Are you sure you want to delete this Info?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-info"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeInfo()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./info.component.ts"></script>
