package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import main.Student;

public interface StudentMapper {
	@Select("select * from student")
	List<Student> select();

	@Select("select * from student where grade=#{value}")
	List<Student> selectGrade(int grade);
	
	@Select("select * from student where studno=#{value}")
	Student selectStudno(int studno);
	
	@Select("select * from student where name=#{value}")
	List<Student> selectName(String name);

//	@Select("select * from student where grade=#{param1} and height>=#{param2}") 
//	List<Student> selectGradeHeight(int grade, int height);
	
	@Select("select * from student where grade=#{grade} and height>=#{height}") 
	List<Student> selectGradeHeight(@Param("grade")int grade, @Param("height")int height);

	@Select("select * from student where grade=#{grade} and height>=#{height}")
	List<Student> selectGradeHeight1(Map<String, Object> map);

	@Insert("insert into student (studno, name, jumin, id) values (#{studno}, #{name}, #{jumin}, #{id})")
	int insert(Student st);
	
	@Update("update student set grade=#{grade}, weight=#{weight}, height=#{height} where studno=#{studno}")
	int update(Student st); //void 가능~ 근데 Student 객체는 안됨
	
	@Delete("delete from student where studno=#{value}")
	int delete(int i);

}
