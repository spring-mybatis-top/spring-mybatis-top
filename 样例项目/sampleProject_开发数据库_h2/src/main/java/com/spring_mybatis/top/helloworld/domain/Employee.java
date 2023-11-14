//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A Employee.
 */
public class Employee {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String empName;

    @Min(value = 1L)
    @Max(value = 100000L)
    private Long salary;

    private Department myDept;
    private Long myDeptId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpName() {
        return this.empName;
    }

    public Employee empName(String empName) {
        this.setEmpName(empName);
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getSalary() {
        return this.salary;
    }

    public Employee salary(Long salary) {
        this.setSalary(salary);
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @JsonIgnore
    public Long getMyDeptId() {
        return this.myDeptId;
    }

    public Department getMyDept() {
        return this.myDept;
    }

    public void setMyDeptId(Long departmentId) {
        this.myDeptId = departmentId;
        // we need to retrieve MyDept or update MyDept is it is different
        if (
            this.myDeptId != null && (this.myDept == null || (this.myDept != null && !Objects.equals(this.myDept.getId(), this.myDeptId)))
        ) {
            this.myDept = SpringContext.getBean(DepartmentMapper.class).selectByPrimaryKey(this.myDeptId);
        }
    }

    public void setMyDept(Department department) {
        this.myDept = department;
        // we need to retrieve myDeptId or update myDeptId is it is different
        if (
            this.myDept != null && (this.myDeptId == null || (this.myDeptId != null && !Objects.equals(this.myDeptId, this.myDept.getId())))
        ) {
            this.myDeptId = this.myDept.getId();
        }
    }

    public Employee myDeptId(Long departmentId) {
        this.setMyDeptId(departmentId);
        return this;
    }

    public Employee myDept(Department department) {
        this.setMyDept(department);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", empName='" + getEmpName() + "'" +
            ", salary=" + getSalary() +
            "}";
    }
}
