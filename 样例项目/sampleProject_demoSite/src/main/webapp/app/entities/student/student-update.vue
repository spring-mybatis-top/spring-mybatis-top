<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sampleProjectAllFildApp.student.home.createOrEditLabel"
          data-cy="StudentCreateUpdateHeading"
          v-text="$t('sampleProjectAllFildApp.student.home.createOrEditLabel')"
        >
          Create or edit a Student
        </h2>
        <div>
          <div class="form-group" v-if="student.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="student.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectAllFildApp.student.stuName')" for="student-stuName">Stu Name</label>
            <input
              type="text"
              class="form-control"
              name="stuName"
              id="student-stuName"
              data-cy="stuName"
              :class="{ valid: !$v.student.stuName.$invalid, invalid: $v.student.stuName.$invalid }"
              v-model="$v.student.stuName.$model"
              required
            />
            <div v-if="$v.student.stuName.$anyDirty && $v.student.stuName.$invalid">
              <small class="form-text text-danger" v-if="!$v.student.stuName.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectAllFildApp.student.money')" for="student-money">Money</label>
            <input
              type="number"
              class="form-control"
              name="money"
              id="student-money"
              data-cy="money"
              :class="{ valid: !$v.student.money.$invalid, invalid: $v.student.money.$invalid }"
              v-model.number="$v.student.money.$model"
            />
            <div v-if="$v.student.money.$anyDirty && $v.student.money.$invalid">
              <small class="form-text text-danger" v-if="!$v.student.money.min" v-text="$t('entity.validation.min', { min: 1 })">
                This field should be at least 1.
              </small>
              <small class="form-text text-danger" v-if="!$v.student.money.max" v-text="$t('entity.validation.max', { max: 100000 })">
                This field cannot be longer than 100000 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.student.money.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sampleProjectAllFildApp.student.language')" for="student-language"
              >Language</label
            >
            <select
              class="form-control"
              name="language"
              :class="{ valid: !$v.student.language.$invalid, invalid: $v.student.language.$invalid }"
              v-model="$v.student.language.$model"
              id="student-language"
              data-cy="language"
            >
              <option
                v-for="language in languageValues"
                :key="language"
                v-bind:value="language"
                v-bind:label="$t('sampleProjectAllFildApp.Language.' + language)"
              >
                {{ language }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="$t('sampleProjectAllFildApp.student.mylesn')" for="student-mylesn">Mylesn</label>
            <select
              class="form-control"
              id="student-mylesns"
              data-cy="mylesn"
              multiple
              name="mylesn"
              v-if="student.mylesns !== undefined"
              v-model="student.mylesns"
            >
              <option v-bind:value="getSelected(student.mylesns, lessonOption)" v-for="lessonOption in lessons" :key="lessonOption.id">
                {{ lessonOption.lesnName }}
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
            :disabled="$v.student.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./student-update.component.ts"></script>
