//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

public class RelStudentMylesnKey {
    private Long studentId;

    private Long mylesnId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public RelStudentMylesnKey studentId(Long studentId) {
        this.setStudentId(studentId);
        return this;
    }

    public Long getMylesnId() {
        return mylesnId;
    }

    public void setMylesnId(Long mylesnId) {
        this.mylesnId = mylesnId;
    }

    public RelStudentMylesnKey  mylesnId(Long mylesnId) {
        this.setMylesnId(mylesnId);
        return this;
    }
}
