<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sampleProjectH2DiskApp.person.home.createOrEditLabel"
          data-cy="PersonCreateUpdateHeading"
          v-text="$t('sampleProjectH2DiskApp.person.home.createOrEditLabel')"
        >
          Create or edit a Person
        </h2>
        <div>
          <div class="form-group" v-if="person.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="person.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectH2DiskApp.person.personName')" for="person-personName"
              >Person Name</label
            >
            <input
              type="text"
              class="form-control"
              name="personName"
              id="person-personName"
              data-cy="personName"
              :class="{ valid: !$v.person.personName.$invalid, invalid: $v.person.personName.$invalid }"
              v-model="$v.person.personName.$model"
              required
            />
            <div v-if="$v.person.personName.$anyDirty && $v.person.personName.$invalid">
              <small class="form-text text-danger" v-if="!$v.person.personName.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectH2DiskApp.person.age')" for="person-age">Age</label>
            <input
              type="number"
              class="form-control"
              name="age"
              id="person-age"
              data-cy="age"
              :class="{ valid: !$v.person.age.$invalid, invalid: $v.person.age.$invalid }"
              v-model.number="$v.person.age.$model"
            />
            <div v-if="$v.person.age.$anyDirty && $v.person.age.$invalid">
              <small class="form-text text-danger" v-if="!$v.person.age.min" v-text="$t('entity.validation.min', { min: 1 })">
                This field should be at least 1.
              </small>
              <small class="form-text text-danger" v-if="!$v.person.age.max" v-text="$t('entity.validation.max', { max: 200 })">
                This field cannot be longer than 200 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.person.age.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectH2DiskApp.person.myinfo')" for="person-myinfo">Myinfo</label>
            <select class="form-control" id="person-myinfo" data-cy="myinfo" name="myinfo" v-model="person.myinfo">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="person.myinfo && infoOption.id === person.myinfo.id ? person.myinfo : infoOption"
                v-for="infoOption in infos"
                :key="infoOption.id"
              >
                {{ infoOption.comments }}
              </option>
            </select>
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
            :disabled="$v.person.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./person-update.component.ts"></script>
