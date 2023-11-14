<template>
  <div>
    <h2 id="page-heading" data-cy="LessonHeading">
      <span v-text="$t('sampleProjectH2DiskApp.lesson.home.title')" id="lesson-heading">Lessons</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sampleProjectH2DiskApp.lesson.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'LessonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-lesson"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sampleProjectH2DiskApp.lesson.home.createLabel')"> Create a new Lesson </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && lessons && lessons.length === 0">
      <span v-text="$t('sampleProjectH2DiskApp.lesson.home.notFound')">No lessons found</span>
    </div>
    <div class="table-responsive" v-if="lessons && lessons.length > 0">
      <table class="table table-striped" aria-describedby="lessons">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lesnName')">
              <span v-text="$t('sampleProjectH2DiskApp.lesson.lesnName')">Lesn Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lesnName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('teacherName')">
              <span v-text="$t('sampleProjectH2DiskApp.lesson.teacherName')">Teacher Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'teacherName'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lesson in lessons" :key="lesson.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LessonView', params: { lessonId: lesson.id } }">{{ lesson.id }}</router-link>
            </td>
            <td>{{ lesson.lesnName }}</td>
            <td>{{ lesson.teacherName }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LessonView', params: { lessonId: lesson.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LessonEdit', params: { lessonId: lesson.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(lesson)"
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
        ><span id="sampleProjectH2DiskApp.lesson.delete.question" data-cy="lessonDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-lesson-heading" v-text="$t('sampleProjectH2DiskApp.lesson.delete.question', { id: removeId })">
          Are you sure you want to delete this Lesson?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-lesson"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLesson()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./lesson.component.ts"></script>
