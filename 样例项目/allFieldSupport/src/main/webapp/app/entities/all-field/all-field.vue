<template>
  <div>
    <h2 id="page-heading" data-cy="AllFieldHeading">
      <span v-text="$t('allFieldSupportApp.allField.home.title')" id="all-field-heading">All Fields</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('allFieldSupportApp.allField.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'AllFieldCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-all-field"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('allFieldSupportApp.allField.home.createLabel')"> Create a new All Field </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && allFields && allFields.length === 0">
      <span v-text="$t('allFieldSupportApp.allField.home.notFound')">No allFields found</span>
    </div>
    <div class="table-responsive" v-if="allFields && allFields.length > 0">
      <table class="table table-striped" aria-describedby="allFields">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.allLowercaseRegex')">All Lowercase Regex</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameInteger')">Name Integer</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameLong')">Name Long</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameBigDecimal')">Name Big Decimal</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameFloat')">Name Float</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameDouble')">Name Double</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameBoolean')">Name Boolean</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameLocalDate')">Name Local Date</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameZonedDateTime')">Name Zoned Date Time</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameInstant')">Name Instant</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameBlob')">Name Blob</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameAnyBlob')">Name Any Blob</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameImageBlob')">Name Image Blob</span></th>
            <th scope="row"><span v-text="$t('allFieldSupportApp.allField.nameTextBlob')">Name Text Blob</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="allField in allFields" :key="allField.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AllFieldView', params: { allFieldId: allField.id } }">{{ allField.id }}</router-link>
            </td>
            <td>{{ allField.allLowercaseRegex }}</td>
            <td>{{ allField.nameInteger }}</td>
            <td>{{ allField.nameLong }}</td>
            <td>{{ allField.nameBigDecimal }}</td>
            <td>{{ allField.nameFloat }}</td>
            <td>{{ allField.nameDouble }}</td>
            <td>{{ allField.nameBoolean }}</td>
            <td>{{ allField.nameLocalDate }}</td>
            <td>{{ allField.nameZonedDateTime ? $d(Date.parse(allField.nameZonedDateTime), 'short') : '' }}</td>
            <td>{{ allField.nameInstant ? $d(Date.parse(allField.nameInstant), 'short') : '' }}</td>
            <td>
              <a
                v-if="allField.nameBlob"
                v-on:click="openFile(allField.nameBlobContentType, allField.nameBlob)"
                v-text="$t('entity.action.open')"
                >open</a
              >
              <span v-if="allField.nameBlob">{{ allField.nameBlobContentType }}, {{ byteSize(allField.nameBlob) }}</span>
            </td>
            <td>
              <a
                v-if="allField.nameAnyBlob"
                v-on:click="openFile(allField.nameAnyBlobContentType, allField.nameAnyBlob)"
                v-text="$t('entity.action.open')"
                >open</a
              >
              <span v-if="allField.nameAnyBlob">{{ allField.nameAnyBlobContentType }}, {{ byteSize(allField.nameAnyBlob) }}</span>
            </td>
            <td>
              <a v-if="allField.nameImageBlob" v-on:click="openFile(allField.nameImageBlobContentType, allField.nameImageBlob)">
                <img
                  v-bind:src="'data:' + allField.nameImageBlobContentType + ';base64,' + allField.nameImageBlob"
                  style="max-height: 30px"
                  alt="allField image"
                />
              </a>
              <span v-if="allField.nameImageBlob">{{ allField.nameImageBlobContentType }}, {{ byteSize(allField.nameImageBlob) }}</span>
            </td>
            <td>{{ allField.nameTextBlob }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'AllFieldView', params: { allFieldId: allField.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'AllFieldEdit', params: { allFieldId: allField.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(allField)"
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
        ><span id="allFieldSupportApp.allField.delete.question" data-cy="allFieldDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-allField-heading" v-text="$t('allFieldSupportApp.allField.delete.question', { id: removeId })">
          Are you sure you want to delete this All Field?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-allField"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeAllField()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./all-field.component.ts"></script>
