<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="helloWorldApp.employee.home.createOrEditLabel"
          data-cy="EmployeeCreateUpdateHeading"
          v-text="$t('helloWorldApp.employee.home.createOrEditLabel')"
        >
          Create or edit a Employee
        </h2>
        <div>
          <div class="form-group" v-if="employee.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="employee.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('helloWorldApp.employee.empName')" for="employee-empName">Emp Name</label>
            <input
              type="text"
              class="form-control"
              name="empName"
              id="employee-empName"
              data-cy="empName"
              :class="{ valid: !$v.employee.empName.$invalid, invalid: $v.employee.empName.$invalid }"
              v-model="$v.employee.empName.$model"
              required
            />
            <div v-if="$v.employee.empName.$anyDirty && $v.employee.empName.$invalid">
              <small class="form-text text-danger" v-if="!$v.employee.empName.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('helloWorldApp.employee.salary')" for="employee-salary">Salary</label>
            <input
              type="number"
              class="form-control"
              name="salary"
              id="employee-salary"
              data-cy="salary"
              :class="{ valid: !$v.employee.salary.$invalid, invalid: $v.employee.salary.$invalid }"
              v-model.number="$v.employee.salary.$model"
            />
            <div v-if="$v.employee.salary.$anyDirty && $v.employee.salary.$invalid">
              <small class="form-text text-danger" v-if="!$v.employee.salary.min" v-text="$t('entity.validation.min', { min: 1 })">
                This field should be at least 1.
              </small>
              <small class="form-text text-danger" v-if="!$v.employee.salary.max" v-text="$t('entity.validation.max', { max: 100000 })">
                This field cannot be longer than 100000 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.employee.salary.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('helloWorldApp.employee.myDept')" for="employee-myDept">My Dept</label>
            <select class="form-control" id="employee-myDept" data-cy="myDept" name="myDept" v-model="employee.myDept">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="employee.myDept && departmentOption.id === employee.myDept.id ? employee.myDept : departmentOption"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.deptName }}
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
            :disabled="$v.employee.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./employee-update.component.ts"></script>
