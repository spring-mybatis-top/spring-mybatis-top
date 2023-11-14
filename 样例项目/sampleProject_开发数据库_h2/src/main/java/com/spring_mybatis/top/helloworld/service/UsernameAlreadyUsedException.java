//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.service;

public class UsernameAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super("Login name already used!");
    }
}
