//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A Department.
 */
public class Department {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String deptName;

    private Integer empNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Department id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public Department deptName(String deptName) {
        this.setDeptName(deptName);
        return this;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getEmpNo() {
        return this.empNo;
    }

    public Department empNo(Integer empNo) {
        this.setEmpNo(empNo);
        return this;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", deptName='" + getDeptName() + "'" +
            ", empNo=" + getEmpNo() +
            "}";
    }
}
