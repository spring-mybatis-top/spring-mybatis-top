//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring_mybatis.top.sample.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AllFieldTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllField.class);
        AllField allField1 = new AllField();
        allField1.setId(1L);
        AllField allField2 = new AllField();
        allField2.setId(allField1.getId());
        assertThat(allField1).isEqualTo(allField2);
        allField2.setId(2L);
        assertThat(allField1).isNotEqualTo(allField2);
        allField1.setId(null);
        assertThat(allField1).isNotEqualTo(allField2);
    }
}
