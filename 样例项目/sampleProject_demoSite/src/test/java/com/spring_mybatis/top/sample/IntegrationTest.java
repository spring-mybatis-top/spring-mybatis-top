//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample;

import com.spring_mybatis.top.sample.SampleProjectAllFildApp;
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
@SpringBootTest(classes = SampleProjectAllFildApp.class)
public @interface IntegrationTest {
}
