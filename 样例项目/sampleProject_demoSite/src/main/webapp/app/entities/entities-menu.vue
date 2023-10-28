<template>
  <div>
    <b-dropdown-item to="/person">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.person')">Person</span>
    </b-dropdown-item>
    <b-dropdown-item to="/info">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.info')">Info</span>
    </b-dropdown-item>
    <b-dropdown-item to="/employee">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.employee')">Employee</span>
    </b-dropdown-item>
    <b-dropdown-item to="/department">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.department')">Department</span>
    </b-dropdown-item>
    <b-dropdown-item to="/student">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.student')">Student</span>
    </b-dropdown-item>
    <b-dropdown-item to="/lesson">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.lesson')">Lesson</span>
    </b-dropdown-item>
    <b-dropdown-item to="/all-field">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.allField')">All Field</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
