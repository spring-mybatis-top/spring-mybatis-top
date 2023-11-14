<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sampleProjectH2DiskApp.lesson.home.createOrEditLabel"
          data-cy="LessonCreateUpdateHeading"
          v-text="$t('sampleProjectH2DiskApp.lesson.home.createOrEditLabel')"
        >
          Create or edit a Lesson
        </h2>
        <div>
          <div class="form-group" v-if="lesson.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="lesson.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectH2DiskApp.lesson.lesnName')" for="lesson-lesnName">Lesn Name</label>
            <input
              type="text"
              class="form-control"
              name="lesnName"
              id="lesson-lesnName"
              data-cy="lesnName"
              :class="{ valid: !$v.lesson.lesnName.$invalid, invalid: $v.lesson.lesnName.$invalid }"
              v-model="$v.lesson.lesnName.$model"
              required
            />
            <div v-if="$v.lesson.lesnName.$anyDirty && $v.lesson.lesnName.$invalid">
              <small class="form-text text-danger" v-if="!$v.lesson.lesnName.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectH2DiskApp.lesson.teacherName')" for="lesson-teacherName"
              >Teacher Name</label
            >
            <input
              type="text"
              class="form-control"
              name="teacherName"
              id="lesson-teacherName"
              data-cy="teacherName"
              :class="{ valid: !$v.lesson.teacherName.$invalid, invalid: $v.lesson.teacherName.$invalid }"
              v-model="$v.lesson.teacherName.$model"
              required
            />
            <div v-if="$v.lesson.teacherName.$anyDirty && $v.lesson.teacherName.$invalid">
              <small class="form-text text-danger" v-if="!$v.lesson.teacherName.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.lesson.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./lesson-update.component.ts"></script>
