<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="lesson">
        <h2 class="jh-entity-heading" data-cy="lessonDetailsHeading">
          <span v-text="$t('sampleProjectMysqlApp.lesson.detail.title')">Lesson</span> {{ lesson.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sampleProjectMysqlApp.lesson.lesnName')">Lesn Name</span>
          </dt>
          <dd>
            <span>{{ lesson.lesnName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sampleProjectMysqlApp.lesson.teacherName')">Teacher Name</span>
          </dt>
          <dd>
            <span>{{ lesson.teacherName }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="lesson.id" :to="{ name: 'LessonEdit', params: { lessonId: lesson.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./lesson-details.component.ts"></script>
