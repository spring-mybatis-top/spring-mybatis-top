//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A Info.
 */
public class Info {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String comments;

    @NotNull
    private String image;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Info id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return this.comments;
    }

    public Info comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImage() {
        return this.image;
    }

    public Info image(String image) {
        this.setImage(image);
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Info)) {
            return false;
        }
        return id != null && id.equals(((Info) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Info{" +
            "id=" + getId() +
            ", comments='" + getComments() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }
}
