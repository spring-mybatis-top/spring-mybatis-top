<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="helloWorldApp.info.home.createOrEditLabel"
          data-cy="InfoCreateUpdateHeading"
          v-text="$t('helloWorldApp.info.home.createOrEditLabel')"
        >
          Create or edit a Info
        </h2>
        <div>
          <div class="form-group" v-if="info.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="info.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('helloWorldApp.info.comments')" for="info-comments">Comments</label>
            <input
              type="text"
              class="form-control"
              name="comments"
              id="info-comments"
              data-cy="comments"
              :class="{ valid: !$v.info.comments.$invalid, invalid: $v.info.comments.$invalid }"
              v-model="$v.info.comments.$model"
              required
            />
            <div v-if="$v.info.comments.$anyDirty && $v.info.comments.$invalid">
              <small class="form-text text-danger" v-if="!$v.info.comments.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('helloWorldApp.info.image')" for="info-image">Image</label>
            <input
              type="text"
              class="form-control"
              name="image"
              id="info-image"
              data-cy="image"
              :class="{ valid: !$v.info.image.$invalid, invalid: $v.info.image.$invalid }"
              v-model="$v.info.image.$model"
              required
            />
            <div v-if="$v.info.image.$anyDirty && $v.info.image.$invalid">
              <small class="form-text text-danger" v-if="!$v.info.image.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.info.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./info-update.component.ts"></script>
