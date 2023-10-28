//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A Person.
 */
public class Person {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String personName;

    @Min(value = 1L)
    @Max(value = 200L)
    private Long age;

    private Info myinfo;
    private Long myinfoId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Person id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return this.personName;
    }

    public Person personName(String personName) {
        this.setPersonName(personName);
        return this;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getAge() {
        return this.age;
    }

    public Person age(Long age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @JsonIgnore
    public Long getMyinfoId() {
        return this.myinfoId;
    }

    public Info getMyinfo() {
        return this.myinfo;
    }

    public void setMyinfoId(Long infoId) {
        this.myinfoId = infoId;
        // we need to retrieve Myinfo or update Myinfo is it is different
        if (
            this.myinfoId != null && (this.myinfo == null || (this.myinfo != null && !Objects.equals(this.myinfo.getId(), this.myinfoId)))
        ) {
            this.myinfo = SpringContext.getBean(InfoMapper.class).selectByPrimaryKey(this.myinfoId);
        }
    }

    public void setMyinfo(Info info) {
        this.myinfo = info;
        // we need to retrieve myinfoId or update myinfoId is it is different
        if (
            this.myinfo != null && (this.myinfoId == null || (this.myinfoId != null && !Objects.equals(this.myinfoId, this.myinfo.getId())))
        ) {
            this.myinfoId = this.myinfo.getId();
        }
    }

    public Person myinfoId(Long infoId) {
        this.setMyinfoId(infoId);
        return this;
    }

    public Person myinfo(Info info) {
        this.setMyinfo(info);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", personName='" + getPersonName() + "'" +
            ", age=" + getAge() +
            "}";
    }
}
