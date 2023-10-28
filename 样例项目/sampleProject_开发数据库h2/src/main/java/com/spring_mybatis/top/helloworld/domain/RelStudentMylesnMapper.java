//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import com.spring_mybatis.top.helloworld.domain.RelStudentMylesnExample;
import com.spring_mybatis.top.helloworld.domain.RelStudentMylesnKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelStudentMylesnMapper {
    long countByExample(RelStudentMylesnExample example);

    int deleteByExample(RelStudentMylesnExample example);

    int deleteByPrimaryKey(RelStudentMylesnKey key);

    int insert(RelStudentMylesnKey row);

    int insertSelective(RelStudentMylesnKey row);

    List<RelStudentMylesnKey> selectByExample(RelStudentMylesnExample example);

    int updateByExampleSelective(@Param("row") RelStudentMylesnKey row, @Param("example") RelStudentMylesnExample example);

    int updateByExample(@Param("row") RelStudentMylesnKey row, @Param("example") RelStudentMylesnExample example);
}