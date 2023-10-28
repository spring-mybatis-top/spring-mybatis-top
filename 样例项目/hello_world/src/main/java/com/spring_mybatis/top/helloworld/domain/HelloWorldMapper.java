//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import com.spring_mybatis.top.helloworld.domain.HelloWorld;
import com.spring_mybatis.top.helloworld.domain.HelloWorldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelloWorldMapper {
    long countByExample(HelloWorldExample example);

    int deleteByExample(HelloWorldExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HelloWorld row);

    int insertSelective(HelloWorld row);

    List<HelloWorld> selectByExample(HelloWorldExample example);

    HelloWorld selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") HelloWorld row, @Param("example") HelloWorldExample example);

    int updateByExample(@Param("row") HelloWorld row, @Param("example") HelloWorldExample example);

    int updateByPrimaryKeySelective(HelloWorld row);

    int updateByPrimaryKey(HelloWorld row);
}