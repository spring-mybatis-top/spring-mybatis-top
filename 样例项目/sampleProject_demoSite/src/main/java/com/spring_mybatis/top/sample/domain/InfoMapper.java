//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import com.spring_mybatis.top.sample.domain.Info;
import com.spring_mybatis.top.sample.domain.InfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoMapper {
    long countByExample(InfoExample example);

    int deleteByExample(InfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Info row);

    int insertSelective(Info row);

    List<Info> selectByExample(InfoExample example);

    Info selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Info row, @Param("example") InfoExample example);

    int updateByExample(@Param("row") Info row, @Param("example") InfoExample example);

    int updateByPrimaryKeySelective(Info row);

    int updateByPrimaryKey(Info row);
}