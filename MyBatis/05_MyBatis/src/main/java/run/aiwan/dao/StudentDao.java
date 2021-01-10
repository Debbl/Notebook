package run.aiwan.dao;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentDao {
    // sql-if
    List<Student> selectStudentIf(Student student);

    // sql-where
    List<Student> selectStudentWhere(Student student);

    // sql-foreach 简单类型
    List<Student> selectForeachOne(List<Integer> idList);

    // sql-foreach 对象类型
    List<Student> selectForeachTwo(List<Student> stuList);

    // sql 代码片段
    List<Student> selectStudentSqlFragment(List<Student> stuList);
}
