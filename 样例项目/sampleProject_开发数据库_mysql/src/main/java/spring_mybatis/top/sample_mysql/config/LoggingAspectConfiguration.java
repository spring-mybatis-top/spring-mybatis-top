//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import spring_mybatis.top.sample_mysql.aop.logging.LoggingAspect;
import tech.jhipster.config.JHipsterConstants;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
