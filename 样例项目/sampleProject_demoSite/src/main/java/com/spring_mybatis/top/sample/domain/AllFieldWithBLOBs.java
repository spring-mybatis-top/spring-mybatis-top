//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

public class AllFieldWithBLOBs extends AllField {
    private byte[] nameBlob;

    private byte[] nameAnyBlob;

    private byte[] nameImageBlob;

    private String nameTextBlob;

    public byte[] getNameBlob() {
        return nameBlob;
    }

    public void setNameBlob(byte[] nameBlob) {
        this.nameBlob = nameBlob;
    }

    public byte[] getNameAnyBlob() {
        return nameAnyBlob;
    }

    public void setNameAnyBlob(byte[] nameAnyBlob) {
        this.nameAnyBlob = nameAnyBlob;
    }

    public byte[] getNameImageBlob() {
        return nameImageBlob;
    }

    public void setNameImageBlob(byte[] nameImageBlob) {
        this.nameImageBlob = nameImageBlob;
    }

    public String getNameTextBlob() {
        return nameTextBlob;
    }

    public void setNameTextBlob(String nameTextBlob) {
        this.nameTextBlob = nameTextBlob;
    }
}