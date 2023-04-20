package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;

public class main6 {
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
	private static Class<StudentMapper> cls = StudentMapper.class;
	private static Map<String, Object> map = new HashMap<>();
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("증즨긔 학생 추가하기");
		Student st = new Student();
		st.setStudno(1001);
		st.setName("증즨긔");
		st.setJumin("9684546115841");
		st.setId("chatjjk96");
		int cnt = session.getMapper(cls).insert(st);
		System.out.println(cnt + "건 학생 정보에 추가");
		Student dbst = session.getMapper(cls).selectStudno(1001);
		System.out.println(dbst);
		
		System.out.println("증즨긔 학생의 학년 1, 몸무게 50 키 140으로 수정하기");
		st.setGrade(1);
		st.setWeight(50);
		st.setHeight(140);
		cnt = session.getMapper(cls).update(st);
		System.out.println(cnt + "건 학생 정보 업데이트");
		dbst = session.getMapper(cls).selectStudno(1001);
		System.out.println(dbst);
		
		System.out.println("증즨긔 학생 없애버리기");
		cnt = session.getMapper(cls).delete(1001);
		System.out.println(cnt + "건 삭제");
		dbst = session.getMapper(cls).selectStudno(1001);
		System.out.println(dbst);
	}
}
