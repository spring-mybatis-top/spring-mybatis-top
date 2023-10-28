<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="allFieldSupportApp.allField.home.createOrEditLabel"
          data-cy="AllFieldCreateUpdateHeading"
          v-text="$t('allFieldSupportApp.allField.home.createOrEditLabel')"
        >
          Create or edit a AllField
        </h2>
        <div>
          <div class="form-group" v-if="allField.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="allField.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.allLowercaseRegex')" for="all-field-allLowercaseRegex"
              >All Lowercase Regex</label
            >
            <input
              type="text"
              class="form-control"
              name="allLowercaseRegex"
              id="all-field-allLowercaseRegex"
              data-cy="allLowercaseRegex"
              :class="{ valid: !$v.allField.allLowercaseRegex.$invalid, invalid: $v.allField.allLowercaseRegex.$invalid }"
              v-model="$v.allField.allLowercaseRegex.$model"
              required
            />
            <div v-if="$v.allField.allLowercaseRegex.$anyDirty && $v.allField.allLowercaseRegex.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.allLowercaseRegex.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.allField.allLowercaseRegex.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'All Lowercase Regex' })"
              >
                This field should follow pattern for "All Lowercase Regex".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameInteger')" for="all-field-nameInteger"
              >Name Integer</label
            >
            <input
              type="number"
              class="form-control"
              name="nameInteger"
              id="all-field-nameInteger"
              data-cy="nameInteger"
              :class="{ valid: !$v.allField.nameInteger.$invalid, invalid: $v.allField.nameInteger.$invalid }"
              v-model.number="$v.allField.nameInteger.$model"
              required
            />
            <div v-if="$v.allField.nameInteger.$anyDirty && $v.allField.nameInteger.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameInteger.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameInteger.min" v-text="$t('entity.validation.min', { min: 10 })">
                This field should be at least 10.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameInteger.max" v-text="$t('entity.validation.max', { max: 100 })">
                This field cannot be longer than 100 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameInteger.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameLong')" for="all-field-nameLong">Name Long</label>
            <input
              type="number"
              class="form-control"
              name="nameLong"
              id="all-field-nameLong"
              data-cy="nameLong"
              :class="{ valid: !$v.allField.nameLong.$invalid, invalid: $v.allField.nameLong.$invalid }"
              v-model.number="$v.allField.nameLong.$model"
              required
            />
            <div v-if="$v.allField.nameLong.$anyDirty && $v.allField.nameLong.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameLong.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameLong.min" v-text="$t('entity.validation.min', { min: 10 })">
                This field should be at least 10.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameLong.max" v-text="$t('entity.validation.max', { max: 100 })">
                This field cannot be longer than 100 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameLong.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameBigDecimal')" for="all-field-nameBigDecimal"
              >Name Big Decimal</label
            >
            <input
              type="number"
              class="form-control"
              name="nameBigDecimal"
              id="all-field-nameBigDecimal"
              data-cy="nameBigDecimal"
              :class="{ valid: !$v.allField.nameBigDecimal.$invalid, invalid: $v.allField.nameBigDecimal.$invalid }"
              v-model.number="$v.allField.nameBigDecimal.$model"
              required
            />
            <div v-if="$v.allField.nameBigDecimal.$anyDirty && $v.allField.nameBigDecimal.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameBigDecimal.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameBigDecimal.min" v-text="$t('entity.validation.min', { min: 10 })">
                This field should be at least 10.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.allField.nameBigDecimal.max"
                v-text="$t('entity.validation.max', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameBigDecimal.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameFloat')" for="all-field-nameFloat"
              >Name Float</label
            >
            <input
              type="number"
              class="form-control"
              name="nameFloat"
              id="all-field-nameFloat"
              data-cy="nameFloat"
              :class="{ valid: !$v.allField.nameFloat.$invalid, invalid: $v.allField.nameFloat.$invalid }"
              v-model.number="$v.allField.nameFloat.$model"
              required
            />
            <div v-if="$v.allField.nameFloat.$anyDirty && $v.allField.nameFloat.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameFloat.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameFloat.min" v-text="$t('entity.validation.min', { min: 10 })">
                This field should be at least 10.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameFloat.max" v-text="$t('entity.validation.max', { max: 100 })">
                This field cannot be longer than 100 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameFloat.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameDouble')" for="all-field-nameDouble"
              >Name Double</label
            >
            <input
              type="number"
              class="form-control"
              name="nameDouble"
              id="all-field-nameDouble"
              data-cy="nameDouble"
              :class="{ valid: !$v.allField.nameDouble.$invalid, invalid: $v.allField.nameDouble.$invalid }"
              v-model.number="$v.allField.nameDouble.$model"
              required
            />
            <div v-if="$v.allField.nameDouble.$anyDirty && $v.allField.nameDouble.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameDouble.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameDouble.min" v-text="$t('entity.validation.min', { min: 10 })">
                This field should be at least 10.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameDouble.max" v-text="$t('entity.validation.max', { max: 100 })">
                This field cannot be longer than 100 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.allField.nameDouble.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameBoolean')" for="all-field-nameBoolean"
              >Name Boolean</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="nameBoolean"
              id="all-field-nameBoolean"
              data-cy="nameBoolean"
              :class="{ valid: !$v.allField.nameBoolean.$invalid, invalid: $v.allField.nameBoolean.$invalid }"
              v-model="$v.allField.nameBoolean.$model"
              required
            />
            <div v-if="$v.allField.nameBoolean.$anyDirty && $v.allField.nameBoolean.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameBoolean.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameLocalDate')" for="all-field-nameLocalDate"
              >Name Local Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="all-field-nameLocalDate"
                  v-model="$v.allField.nameLocalDate.$model"
                  name="nameLocalDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="all-field-nameLocalDate"
                data-cy="nameLocalDate"
                type="text"
                class="form-control"
                name="nameLocalDate"
                :class="{ valid: !$v.allField.nameLocalDate.$invalid, invalid: $v.allField.nameLocalDate.$invalid }"
                v-model="$v.allField.nameLocalDate.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.allField.nameLocalDate.$anyDirty && $v.allField.nameLocalDate.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameLocalDate.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameZonedDateTime')" for="all-field-nameZonedDateTime"
              >Name Zoned Date Time</label
            >
            <div class="d-flex">
              <input
                id="all-field-nameZonedDateTime"
                data-cy="nameZonedDateTime"
                type="datetime-local"
                class="form-control"
                name="nameZonedDateTime"
                :class="{ valid: !$v.allField.nameZonedDateTime.$invalid, invalid: $v.allField.nameZonedDateTime.$invalid }"
                required
                :value="convertDateTimeFromServer($v.allField.nameZonedDateTime.$model)"
                @change="updateZonedDateTimeField('nameZonedDateTime', $event)"
              />
            </div>
            <div v-if="$v.allField.nameZonedDateTime.$anyDirty && $v.allField.nameZonedDateTime.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameZonedDateTime.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.allField.nameZonedDateTime.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameInstant')" for="all-field-nameInstant"
              >Name Instant</label
            >
            <div class="d-flex">
              <input
                id="all-field-nameInstant"
                data-cy="nameInstant"
                type="datetime-local"
                class="form-control"
                name="nameInstant"
                :class="{ valid: !$v.allField.nameInstant.$invalid, invalid: $v.allField.nameInstant.$invalid }"
                required
                :value="convertDateTimeFromServer($v.allField.nameInstant.$model)"
                @change="updateInstantField('nameInstant', $event)"
              />
            </div>
            <div v-if="$v.allField.nameInstant.$anyDirty && $v.allField.nameInstant.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameInstant.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.allField.nameInstant.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameBlob')" for="all-field-nameBlob">Name Blob</label>
            <div>
              <div v-if="allField.nameBlob" class="form-text text-danger clearfix">
                <a
                  class="pull-left"
                  v-on:click="openFile(allField.nameBlobContentType, allField.nameBlob)"
                  v-text="$t('entity.action.open')"
                  >open</a
                ><br />
                <span class="pull-left">{{ allField.nameBlobContentType }}, {{ byteSize(allField.nameBlob) }}</span>
                <button
                  type="button"
                  v-on:click="
                    allField.nameBlob = null;
                    allField.nameBlobContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_nameBlob"
                id="file_nameBlob"
                data-cy="nameBlob"
                v-on:change="setFileData($event, allField, 'nameBlob', false)"
                v-text="$t('entity.action.addblob')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="nameBlob"
              id="all-field-nameBlob"
              data-cy="nameBlob"
              :class="{ valid: !$v.allField.nameBlob.$invalid, invalid: $v.allField.nameBlob.$invalid }"
              v-model="$v.allField.nameBlob.$model"
              required
            />
            <input
              type="hidden"
              class="form-control"
              name="nameBlobContentType"
              id="all-field-nameBlobContentType"
              v-model="allField.nameBlobContentType"
            />
            <div v-if="$v.allField.nameBlob.$anyDirty && $v.allField.nameBlob.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameBlob.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameAnyBlob')" for="all-field-nameAnyBlob"
              >Name Any Blob</label
            >
            <div>
              <div v-if="allField.nameAnyBlob" class="form-text text-danger clearfix">
                <a
                  class="pull-left"
                  v-on:click="openFile(allField.nameAnyBlobContentType, allField.nameAnyBlob)"
                  v-text="$t('entity.action.open')"
                  >open</a
                ><br />
                <span class="pull-left">{{ allField.nameAnyBlobContentType }}, {{ byteSize(allField.nameAnyBlob) }}</span>
                <button
                  type="button"
                  v-on:click="
                    allField.nameAnyBlob = null;
                    allField.nameAnyBlobContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_nameAnyBlob"
                id="file_nameAnyBlob"
                data-cy="nameAnyBlob"
                v-on:change="setFileData($event, allField, 'nameAnyBlob', false)"
                v-text="$t('entity.action.addblob')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="nameAnyBlob"
              id="all-field-nameAnyBlob"
              data-cy="nameAnyBlob"
              :class="{ valid: !$v.allField.nameAnyBlob.$invalid, invalid: $v.allField.nameAnyBlob.$invalid }"
              v-model="$v.allField.nameAnyBlob.$model"
              required
            />
            <input
              type="hidden"
              class="form-control"
              name="nameAnyBlobContentType"
              id="all-field-nameAnyBlobContentType"
              v-model="allField.nameAnyBlobContentType"
            />
            <div v-if="$v.allField.nameAnyBlob.$anyDirty && $v.allField.nameAnyBlob.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameAnyBlob.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameImageBlob')" for="all-field-nameImageBlob"
              >Name Image Blob</label
            >
            <div>
              <img
                v-bind:src="'data:' + allField.nameImageBlobContentType + ';base64,' + allField.nameImageBlob"
                style="max-height: 100px"
                v-if="allField.nameImageBlob"
                alt="allField image"
              />
              <div v-if="allField.nameImageBlob" class="form-text text-danger clearfix">
                <span class="pull-left">{{ allField.nameImageBlobContentType }}, {{ byteSize(allField.nameImageBlob) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('nameImageBlob', 'nameImageBlobContentType', 'file_nameImageBlob')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_nameImageBlob"
                id="file_nameImageBlob"
                data-cy="nameImageBlob"
                v-on:change="setFileData($event, allField, 'nameImageBlob', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="nameImageBlob"
              id="all-field-nameImageBlob"
              data-cy="nameImageBlob"
              :class="{ valid: !$v.allField.nameImageBlob.$invalid, invalid: $v.allField.nameImageBlob.$invalid }"
              v-model="$v.allField.nameImageBlob.$model"
              required
            />
            <input
              type="hidden"
              class="form-control"
              name="nameImageBlobContentType"
              id="all-field-nameImageBlobContentType"
              v-model="allField.nameImageBlobContentType"
            />
            <div v-if="$v.allField.nameImageBlob.$anyDirty && $v.allField.nameImageBlob.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameImageBlob.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('allFieldSupportApp.allField.nameTextBlob')" for="all-field-nameTextBlob"
              >Name Text Blob</label
            >
            <textarea
              class="form-control"
              name="nameTextBlob"
              id="all-field-nameTextBlob"
              data-cy="nameTextBlob"
              :class="{ valid: !$v.allField.nameTextBlob.$invalid, invalid: $v.allField.nameTextBlob.$invalid }"
              v-model="$v.allField.nameTextBlob.$model"
              required
            ></textarea>
            <div v-if="$v.allField.nameTextBlob.$anyDirty && $v.allField.nameTextBlob.$invalid">
              <small class="form-text text-danger" v-if="!$v.allField.nameTextBlob.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.allField.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./all-field-update.component.ts"></script>
