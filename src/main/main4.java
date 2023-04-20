package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;
/*
 인터페이스 방식으로 sql Mapper 설정하기
 namespace : mapper.StudentMapper
 메서드 이름이 sql 구문의 이름이 됨 => 같은 이름을 가진 메서드 허용 안함(오버로딩 x)
 */
public class main4 {
	private final static SqlSessionFactory sqlMap;
	static {
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	private static Class<StudentMapper> cls = StudentMapper.class; //클래스정보를 가지고 있는 클래스
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("모든 학생 정보 조회하기");
		List<Student> list = session.getMapper(cls).select();
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생 정보 조회하기");
		list = session.getMapper(cls).selectGrade(1);
		for(Student s : list) System.out.println(s);
		
		System.out.println("970111 학생 정보 조회하기");
		Student s = session.getMapper(cls).selectStudno(970111);
		System.out.println(s);
		
//		System.out.println("이름이 진영훈인 학생 정보 조회하기");
//		list = session.getMapper(cls).select("진영훈"); //interface방식을 사용할 때 overloading하면 안됨. static 초기화에서 걸림(?)
		System.out.println("이름이 진영훈인 학생 정보 조회하기");
		list = session.getMapper(cls).selectName("진영훈");
		for(Student st : list) System.out.println(st);
		
		System.out.println("1학년 학생 중 키가 180 이상인 정보 조회(Map으로)");
		Map<String, Object> map = new HashMap<>();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.getMapper(cls).selectGradeHeight1(map);
		for(Student st : list) System.out.println(st);
		
		System.out.println("1학년 학생 중 키가 180 이상인 정보 조회(parameter로)");
		list = session.getMapper(cls).selectGradeHeight(1,180);
		for(Student st : list) System.out.println(st);
	}
}
