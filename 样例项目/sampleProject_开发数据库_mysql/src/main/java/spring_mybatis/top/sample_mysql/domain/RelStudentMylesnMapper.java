//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.domain;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import spring_mybatis.top.sample_mysql.domain.RelStudentMylesnExample;
import spring_mybatis.top.sample_mysql.domain.RelStudentMylesnKey;

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