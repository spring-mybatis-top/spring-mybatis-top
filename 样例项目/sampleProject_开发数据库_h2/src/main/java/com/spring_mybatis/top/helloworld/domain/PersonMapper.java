//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.domain;

import com.spring_mybatis.top.helloworld.domain.Person;
import com.spring_mybatis.top.helloworld.domain.PersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    long countByExample(PersonExample example);

    int deleteByExample(PersonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Person row);

    int insertSelective(Person row);

    List<Person> selectByExample(PersonExample example);

    Person selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Person row, @Param("example") PersonExample example);

    int updateByExample(@Param("row") Person row, @Param("example") PersonExample example);

    int updateByPrimaryKeySelective(Person row);

    int updateByPrimaryKey(Person row);
}