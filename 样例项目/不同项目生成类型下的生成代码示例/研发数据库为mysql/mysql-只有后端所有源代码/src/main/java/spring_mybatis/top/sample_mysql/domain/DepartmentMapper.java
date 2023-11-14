//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.domain;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import spring_mybatis.top.sample_mysql.domain.Department;
import spring_mybatis.top.sample_mysql.domain.DepartmentExample;

public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Department row);

    int insertSelective(Department row);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Department row, @Param("example") DepartmentExample example);

    int updateByExample(@Param("row") Department row, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department row);

    int updateByPrimaryKey(Department row);
}