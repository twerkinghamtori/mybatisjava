package main;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch(IOException e) {
			e.printStackTrace();
		}
		SqlSession session = sqlMap.openSession();
		int cnt = (Integer)session.selectOne("student.count");
		System.out.println("student 테이블의 레코드 건수 : " + cnt);
		
		System.out.println("학생테이블 등록된 레코드 정보");
		List<Student> list = session.selectList("student.list");
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생의 정보");
		list = session.selectList("student.selectGrade" , 1);
		for(Student s : list) System.out.println(s);
		
		System.out.println("성이 김씨인 학생의 정보");
		list = session.selectList("student.selectName" , "김%");
		for(Student s : list) System.out.println(s);
		
		System.out.println("3학년 학생 중 주민번호 기준 여학생 정보");
		Map<String, Object> map = new HashMap<>();
		map.put("grade",3);
		map.put("jumin", 2);
		list = session.selectList("student.selectGradeGender",map);
		for(Student s : list) System.out.println(s);
		
	}

}
