<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="allField">
        <h2 class="jh-entity-heading" data-cy="allFieldDetailsHeading">
          <span v-text="$t('allFieldSupportApp.allField.detail.title')">AllField</span> {{ allField.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.allLowercaseRegex')">All Lowercase Regex</span>
          </dt>
          <dd>
            <span>{{ allField.allLowercaseRegex }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameInteger')">Name Integer</span>
          </dt>
          <dd>
            <span>{{ allField.nameInteger }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameLong')">Name Long</span>
          </dt>
          <dd>
            <span>{{ allField.nameLong }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameBigDecimal')">Name Big Decimal</span>
          </dt>
          <dd>
            <span>{{ allField.nameBigDecimal }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameFloat')">Name Float</span>
          </dt>
          <dd>
            <span>{{ allField.nameFloat }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameDouble')">Name Double</span>
          </dt>
          <dd>
            <span>{{ allField.nameDouble }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameBoolean')">Name Boolean</span>
          </dt>
          <dd>
            <span>{{ allField.nameBoolean }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameLocalDate')">Name Local Date</span>
          </dt>
          <dd>
            <span>{{ allField.nameLocalDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameZonedDateTime')">Name Zoned Date Time</span>
          </dt>
          <dd>
            <span v-if="allField.nameZonedDateTime">{{ $d(Date.parse(allField.nameZonedDateTime), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameInstant')">Name Instant</span>
          </dt>
          <dd>
            <span v-if="allField.nameInstant">{{ $d(Date.parse(allField.nameInstant), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameBlob')">Name Blob</span>
          </dt>
          <dd>
            <div v-if="allField.nameBlob">
              <a v-on:click="openFile(allField.nameBlobContentType, allField.nameBlob)" v-text="$t('entity.action.open')">open</a>
              {{ allField.nameBlobContentType }}, {{ byteSize(allField.nameBlob) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameAnyBlob')">Name Any Blob</span>
          </dt>
          <dd>
            <div v-if="allField.nameAnyBlob">
              <a v-on:click="openFile(allField.nameAnyBlobContentType, allField.nameAnyBlob)" v-text="$t('entity.action.open')">open</a>
              {{ allField.nameAnyBlobContentType }}, {{ byteSize(allField.nameAnyBlob) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameImageBlob')">Name Image Blob</span>
          </dt>
          <dd>
            <div v-if="allField.nameImageBlob">
              <a v-on:click="openFile(allField.nameImageBlobContentType, allField.nameImageBlob)">
                <img
                  v-bind:src="'data:' + allField.nameImageBlobContentType + ';base64,' + allField.nameImageBlob"
                  style="max-width: 100%"
                  alt="allField image"
                />
              </a>
              {{ allField.nameImageBlobContentType }}, {{ byteSize(allField.nameImageBlob) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('allFieldSupportApp.allField.nameTextBlob')">Name Text Blob</span>
          </dt>
          <dd>
            <span>{{ allField.nameTextBlob }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="allField.id" :to="{ name: 'AllFieldEdit', params: { allFieldId: allField.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./all-field-details.component.ts"></script>
