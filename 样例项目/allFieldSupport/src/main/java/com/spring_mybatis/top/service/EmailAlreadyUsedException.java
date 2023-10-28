//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.service;

public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }
}
