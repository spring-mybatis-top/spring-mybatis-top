<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="student">
        <h2 class="jh-entity-heading" data-cy="studentDetailsHeading">
          <span v-text="$t('sampleProjectAllFildApp.student.detail.title')">Student</span> {{ student.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sampleProjectAllFildApp.student.stuName')">Stu Name</span>
          </dt>
          <dd>
            <span>{{ student.stuName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sampleProjectAllFildApp.student.money')">Money</span>
          </dt>
          <dd>
            <span>{{ student.money }}</span>
          </dd>
          <dt>
            <span v-text="$t('sampleProjectAllFildApp.student.language')">Language</span>
          </dt>
          <dd>
            <span v-text="$t('sampleProjectAllFildApp.Language.' + student.language)">{{ student.language }}</span>
          </dd>
          <dt>
            <span v-text="$t('sampleProjectAllFildApp.student.mylesn')">Mylesn</span>
          </dt>
          <dd>
            <span v-for="(mylesn, i) in student.mylesns" :key="mylesn.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'LessonView', params: { lessonId: mylesn.id } }">{{ mylesn.lesnName }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="student.id" :to="{ name: 'StudentEdit', params: { studentId: student.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./student-details.component.ts"></script>
