<template>
  <div>
    <h2 id="page-heading" data-cy="StudentHeading">
      <span v-text="$t('sampleProjectAllFildApp.student.home.title')" id="student-heading">Students</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sampleProjectAllFildApp.student.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'StudentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-student"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sampleProjectAllFildApp.student.home.createLabel')"> Create a new Student </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && students && students.length === 0">
      <span v-text="$t('sampleProjectAllFildApp.student.home.notFound')">No students found</span>
    </div>
    <div class="table-responsive" v-if="students && students.length > 0">
      <table class="table table-striped" aria-describedby="students">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('stuName')">
              <span v-text="$t('sampleProjectAllFildApp.student.stuName')">Stu Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'stuName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('money')">
              <span v-text="$t('sampleProjectAllFildApp.student.money')">Money</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'money'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('language')">
              <span v-text="$t('sampleProjectAllFildApp.student.language')">Language</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'language'"></jhi-sort-indicator>
            </th>
            <th scope="row"><span v-text="$t('sampleProjectAllFildApp.student.mylesn')">Mylesn</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in students" :key="student.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'StudentView', params: { studentId: student.id } }">{{ student.id }}</router-link>
            </td>
            <td>{{ student.stuName }}</td>
            <td>{{ student.money }}</td>
            <td v-text="$t('sampleProjectAllFildApp.Language.' + student.language)">{{ student.language }}</td>
            <td>
              <span v-for="(mylesn, i) in student.mylesns" :key="mylesn.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'LessonView', params: { lessonId: mylesn.id } }">{{
                  mylesn.lesnName
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'StudentView', params: { studentId: student.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'StudentEdit', params: { studentId: student.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(student)"
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
        ><span id="sampleProjectAllFildApp.student.delete.question" data-cy="studentDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-student-heading" v-text="$t('sampleProjectAllFildApp.student.delete.question', { id: removeId })">
          Are you sure you want to delete this Student?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-student"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeStudent()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="students && students.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./student.component.ts"></script>
