//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring_mybatis.top.helloworld.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Info.class);
        Info info1 = new Info();
        info1.setId(1L);
        Info info2 = new Info();
        info2.setId(info1.getId());
        assertThat(info1).isEqualTo(info2);
        info2.setId(2L);
        assertThat(info1).isNotEqualTo(info2);
        info1.setId(null);
        assertThat(info1).isNotEqualTo(info2);
    }
}
