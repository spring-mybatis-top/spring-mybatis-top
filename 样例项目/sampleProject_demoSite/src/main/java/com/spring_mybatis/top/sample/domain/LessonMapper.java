//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import com.spring_mybatis.top.sample.domain.Lesson;
import com.spring_mybatis.top.sample.domain.LessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LessonMapper {
    long countByExample(LessonExample example);

    int deleteByExample(LessonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Lesson row);

    int insertSelective(Lesson row);

    List<Lesson> selectByExample(LessonExample example);

    Lesson selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Lesson row, @Param("example") LessonExample example);

    int updateByExample(@Param("row") Lesson row, @Param("example") LessonExample example);

    int updateByPrimaryKeySelective(Lesson row);

    int updateByPrimaryKey(Lesson row);
}