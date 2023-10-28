//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A AllField.
 */
public class AllField {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]+d$")
    private String name;

    @NotNull
    @Min(value = 10)
    @Max(value = 100)
    private Integer nameInteger;

    @NotNull
    @Min(value = 10L)
    @Max(value = 100L)
    private Long nameLong;

    @NotNull
    @DecimalMin(value = "10")
    @DecimalMax(value = "100")
    private BigDecimal nameBigDecimal;

    @NotNull
    @DecimalMin(value = "10")
    @DecimalMax(value = "100")
    private Float nameFloat;

    @NotNull
    @DecimalMin(value = "10")
    @DecimalMax(value = "100")
    private Double nameDouble;

    @NotNull
    private Boolean nameBoolean;

    @NotNull
    private LocalDate nameLocalDate;

    @NotNull
    private ZonedDateTime nameZonedDateTime;

    @NotNull
    private Instant nameInstant;

    private byte[] nameBlob;

    @NotNull
    private String nameBlobContentType;

    private byte[] nameAnyBlob;

    @NotNull
    private String nameAnyBlobContentType;

    private byte[] nameImageBlob;

    @NotNull
    private String nameImageBlobContentType;

    private String nameTextBlob;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AllField id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public AllField name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNameInteger() {
        return this.nameInteger;
    }

    public AllField nameInteger(Integer nameInteger) {
        this.setNameInteger(nameInteger);
        return this;
    }

    public void setNameInteger(Integer nameInteger) {
        this.nameInteger = nameInteger;
    }

    public Long getNameLong() {
        return this.nameLong;
    }

    public AllField nameLong(Long nameLong) {
        this.setNameLong(nameLong);
        return this;
    }

    public void setNameLong(Long nameLong) {
        this.nameLong = nameLong;
    }

    public BigDecimal getNameBigDecimal() {
        return this.nameBigDecimal;
    }

    public AllField nameBigDecimal(BigDecimal nameBigDecimal) {
        this.setNameBigDecimal(nameBigDecimal);
        return this;
    }

    public void setNameBigDecimal(BigDecimal nameBigDecimal) {
        this.nameBigDecimal = nameBigDecimal;
    }

    public Float getNameFloat() {
        return this.nameFloat;
    }

    public AllField nameFloat(Float nameFloat) {
        this.setNameFloat(nameFloat);
        return this;
    }

    public void setNameFloat(Float nameFloat) {
        this.nameFloat = nameFloat;
    }

    public Double getNameDouble() {
        return this.nameDouble;
    }

    public AllField nameDouble(Double nameDouble) {
        this.setNameDouble(nameDouble);
        return this;
    }

    public void setNameDouble(Double nameDouble) {
        this.nameDouble = nameDouble;
    }

    public Boolean getNameBoolean() {
        return this.nameBoolean;
    }

    public AllField nameBoolean(Boolean nameBoolean) {
        this.setNameBoolean(nameBoolean);
        return this;
    }

    public void setNameBoolean(Boolean nameBoolean) {
        this.nameBoolean = nameBoolean;
    }

    public LocalDate getNameLocalDate() {
        return this.nameLocalDate;
    }

    public AllField nameLocalDate(LocalDate nameLocalDate) {
        this.setNameLocalDate(nameLocalDate);
        return this;
    }

    public void setNameLocalDate(LocalDate nameLocalDate) {
        this.nameLocalDate = nameLocalDate;
    }

    public ZonedDateTime getNameZonedDateTime() {
        return this.nameZonedDateTime;
    }

    public AllField nameZonedDateTime(ZonedDateTime nameZonedDateTime) {
        this.setNameZonedDateTime(nameZonedDateTime);
        return this;
    }

    public void setNameZonedDateTime(ZonedDateTime nameZonedDateTime) {
        this.nameZonedDateTime = nameZonedDateTime;
    }

    public Instant getNameInstant() {
        return this.nameInstant;
    }

    public AllField nameInstant(Instant nameInstant) {
        this.setNameInstant(nameInstant);
        return this;
    }

    public void setNameInstant(Instant nameInstant) {
        this.nameInstant = nameInstant;
    }

    public byte[] getNameBlob() {
        return this.nameBlob;
    }

    public AllField nameBlob(byte[] nameBlob) {
        this.setNameBlob(nameBlob);
        return this;
    }

    public void setNameBlob(byte[] nameBlob) {
        this.nameBlob = nameBlob;
    }

    public String getNameBlobContentType() {
        return this.nameBlobContentType;
    }

    public AllField nameBlobContentType(String nameBlobContentType) {
        this.nameBlobContentType = nameBlobContentType;
        return this;
    }

    public void setNameBlobContentType(String nameBlobContentType) {
        this.nameBlobContentType = nameBlobContentType;
    }

    public byte[] getNameAnyBlob() {
        return this.nameAnyBlob;
    }

    public AllField nameAnyBlob(byte[] nameAnyBlob) {
        this.setNameAnyBlob(nameAnyBlob);
        return this;
    }

    public void setNameAnyBlob(byte[] nameAnyBlob) {
        this.nameAnyBlob = nameAnyBlob;
    }

    public String getNameAnyBlobContentType() {
        return this.nameAnyBlobContentType;
    }

    public AllField nameAnyBlobContentType(String nameAnyBlobContentType) {
        this.nameAnyBlobContentType = nameAnyBlobContentType;
        return this;
    }

    public void setNameAnyBlobContentType(String nameAnyBlobContentType) {
        this.nameAnyBlobContentType = nameAnyBlobContentType;
    }

    public byte[] getNameImageBlob() {
        return this.nameImageBlob;
    }

    public AllField nameImageBlob(byte[] nameImageBlob) {
        this.setNameImageBlob(nameImageBlob);
        return this;
    }

    public void setNameImageBlob(byte[] nameImageBlob) {
        this.nameImageBlob = nameImageBlob;
    }

    public String getNameImageBlobContentType() {
        return this.nameImageBlobContentType;
    }

    public AllField nameImageBlobContentType(String nameImageBlobContentType) {
        this.nameImageBlobContentType = nameImageBlobContentType;
        return this;
    }

    public void setNameImageBlobContentType(String nameImageBlobContentType) {
        this.nameImageBlobContentType = nameImageBlobContentType;
    }

    public String getNameTextBlob() {
        return this.nameTextBlob;
    }

    public AllField nameTextBlob(String nameTextBlob) {
        this.setNameTextBlob(nameTextBlob);
        return this;
    }

    public void setNameTextBlob(String nameTextBlob) {
        this.nameTextBlob = nameTextBlob;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AllField)) {
            return false;
        }
        return id != null && id.equals(((AllField) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AllField{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameInteger=" + getNameInteger() +
            ", nameLong=" + getNameLong() +
            ", nameBigDecimal=" + getNameBigDecimal() +
            ", nameFloat=" + getNameFloat() +
            ", nameDouble=" + getNameDouble() +
            ", nameBoolean='" + getNameBoolean() + "'" +
            ", nameLocalDate='" + getNameLocalDate() + "'" +
            ", nameZonedDateTime='" + getNameZonedDateTime() + "'" +
            ", nameInstant='" + getNameInstant() + "'" +
            ", nameBlob='" + getNameBlob() + "'" +
            ", nameBlobContentType='" + getNameBlobContentType() + "'" +
            ", nameAnyBlob='" + getNameAnyBlob() + "'" +
            ", nameAnyBlobContentType='" + getNameAnyBlobContentType() + "'" +
            ", nameImageBlob='" + getNameImageBlob() + "'" +
            ", nameImageBlobContentType='" + getNameImageBlobContentType() + "'" +
            ", nameTextBlob='" + getNameTextBlob() + "'" +
            "}";
    }
}
