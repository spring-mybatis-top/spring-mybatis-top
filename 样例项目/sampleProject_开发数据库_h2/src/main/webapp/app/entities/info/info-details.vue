<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="info">
        <h2 class="jh-entity-heading" data-cy="infoDetailsHeading">
          <span v-text="$t('helloWorldApp.info.detail.title')">Info</span> {{ info.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('helloWorldApp.info.comments')">Comments</span>
          </dt>
          <dd>
            <span>{{ info.comments }}</span>
          </dd>
          <dt>
            <span v-text="$t('helloWorldApp.info.image')">Image</span>
          </dt>
          <dd>
            <span>{{ info.image }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="info.id" :to="{ name: 'InfoEdit', params: { infoId: info.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./info-details.component.ts"></script>
