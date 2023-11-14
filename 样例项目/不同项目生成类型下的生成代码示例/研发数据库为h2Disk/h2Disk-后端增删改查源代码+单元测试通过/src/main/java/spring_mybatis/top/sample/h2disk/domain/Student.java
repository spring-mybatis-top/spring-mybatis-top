//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample.h2disk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;
import spring_mybatis.top.sample.h2disk.domain.enumeration.Language;

/**
 * A Student.
 */
public class Student {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String stuName;

    @Min(value = 1L)
    @Max(value = 100000L)
    private Long money;

    private Language language;

    private Set<Lesson> mylesns = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Student id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return this.stuName;
    }

    public Student stuName(String stuName) {
        this.setStuName(stuName);
        return this;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Long getMoney() {
        return this.money;
    }

    public Student money(Long money) {
        this.setMoney(money);
        return this;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Language getLanguage() {
        return this.language;
    }

    public Student language(Language language) {
        this.setLanguage(language);
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Lesson> getMylesns() {
        return this.mylesns;
    }

    public void setMylesns(Set<Lesson> lessons) {
        this.mylesns = lessons;
    }

    public Student mylesns(Set<Lesson> lessons) {
        this.setMylesns(lessons);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", stuName='" + getStuName() + "'" +
            ", money=" + getMoney() +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
