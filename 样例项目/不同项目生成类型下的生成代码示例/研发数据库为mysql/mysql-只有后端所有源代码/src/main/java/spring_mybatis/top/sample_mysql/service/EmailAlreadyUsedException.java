//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.service;

public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }
}
