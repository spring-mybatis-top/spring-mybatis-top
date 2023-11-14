//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import spring_mybatis.top.sample_mysql.web.rest.TestUtil;

class LessonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lesson.class);
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        Lesson lesson2 = new Lesson();
        lesson2.setId(lesson1.getId());
        assertThat(lesson1).isEqualTo(lesson2);
        lesson2.setId(2L);
        assertThat(lesson1).isNotEqualTo(lesson2);
        lesson1.setId(null);
        assertThat(lesson1).isNotEqualTo(lesson2);
    }
}
