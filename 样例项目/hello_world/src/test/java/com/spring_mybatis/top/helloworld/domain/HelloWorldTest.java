//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring_mybatis.top.helloworld.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HelloWorld.class);
        HelloWorld helloWorld1 = new HelloWorld();
        helloWorld1.setId(1L);
        HelloWorld helloWorld2 = new HelloWorld();
        helloWorld2.setId(helloWorld1.getId());
        assertThat(helloWorld1).isEqualTo(helloWorld2);
        helloWorld2.setId(2L);
        assertThat(helloWorld1).isNotEqualTo(helloWorld2);
        helloWorld1.setId(null);
        assertThat(helloWorld1).isNotEqualTo(helloWorld2);
    }
}
