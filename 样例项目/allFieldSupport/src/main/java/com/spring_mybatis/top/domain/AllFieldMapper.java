//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.domain;

import com.spring_mybatis.top.domain.AllField;
import com.spring_mybatis.top.domain.AllFieldExample;
import com.spring_mybatis.top.domain.AllFieldWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AllFieldMapper {
    long countByExample(AllFieldExample example);

    int deleteByExample(AllFieldExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AllFieldWithBLOBs row);

    int insertSelective(AllFieldWithBLOBs row);

    List<AllFieldWithBLOBs> selectByExampleWithBLOBs(AllFieldExample example);

    List<AllField> selectByExample(AllFieldExample example);

    AllFieldWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AllFieldWithBLOBs row, @Param("example") AllFieldExample example);

    int updateByExampleWithBLOBs(@Param("row") AllFieldWithBLOBs row, @Param("example") AllFieldExample example);

    int updateByExample(@Param("row") AllField row, @Param("example") AllFieldExample example);

    int updateByPrimaryKeySelective(AllFieldWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(AllFieldWithBLOBs row);

    int updateByPrimaryKey(AllField row);
}