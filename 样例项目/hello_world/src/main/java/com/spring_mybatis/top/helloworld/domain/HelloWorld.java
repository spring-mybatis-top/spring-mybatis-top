//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * A HelloWorld.
 */
public class HelloWorld {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer age;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HelloWorld id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public HelloWorld name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public HelloWorld age(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HelloWorld)) {
            return false;
        }
        return id != null && id.equals(((HelloWorld) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HelloWorld{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", age=" + getAge() +
            "}";
    }
}
