//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample.h2disk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import spring_mybatis.top.sample.h2disk.SampleProjectH2DiskApp;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = SampleProjectH2DiskApp.class)
public @interface IntegrationTest {
}
