//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample.h2disk.domain;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import spring_mybatis.top.sample.h2disk.domain.Employee;
import spring_mybatis.top.sample.h2disk.domain.EmployeeExample;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Employee row);

    int insertSelective(Employee row);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Employee row, @Param("example") EmployeeExample example);

    int updateByExample(@Param("row") Employee row, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee row);

    int updateByPrimaryKey(Employee row);
}