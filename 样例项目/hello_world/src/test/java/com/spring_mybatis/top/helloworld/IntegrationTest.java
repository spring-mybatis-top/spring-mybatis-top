//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld;

import com.spring_mybatis.top.helloworld.HelloWorldApp;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = HelloWorldApp.class)
public @interface IntegrationTest {
}
