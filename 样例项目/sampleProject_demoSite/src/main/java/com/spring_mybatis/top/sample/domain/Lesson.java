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
 * A Lesson.
 */
public class Lesson {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String lesnName;

    @NotNull
    private String teacherName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Lesson id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLesnName() {
        return this.lesnName;
    }

    public Lesson lesnName(String lesnName) {
        this.setLesnName(lesnName);
        return this;
    }

    public void setLesnName(String lesnName) {
        this.lesnName = lesnName;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public Lesson teacherName(String teacherName) {
        this.setTeacherName(teacherName);
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lesson)) {
            return false;
        }
        return id != null && id.equals(((Lesson) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lesson{" +
            "id=" + getId() +
            ", lesnName='" + getLesnName() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            "}";
    }
}
