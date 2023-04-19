package main;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class main2 {
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
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("학생 테이블에 레코드 추가하기");
		Student st = new Student();
		st.setStudno(1002);
		st.setName("김삿갓");
		st.setGrade(1);
		st.setId("kimsg");
		st.setJumin("9901031234567");
		st.setMajor1(101);
		int cnt = session.insert("student.insert", st); //session.insert => 변경된 레코드 건수
		System.out.println("student 레코드 추가 : "+ cnt);
		Student resultSt = session.selectOne("student.selectNo",st.getStudno());
		System.out.println(resultSt);
//		session.commit(); //실제 레코드 추가 완료. 커밋안하면 insert 여러번 실행해도 오류 안 남(db에 등록 안됨). transaction을 jdbc가 담당하기 때문에 mybatis에서는 직접 transaction을 해줘야함.
		st.setGrade(2);
		st.setHeight(175);
		st.setWeight(80);
		st.setProfno(1001);
		cnt = session.update("student.update", st);
		System.out.println("student 테이블 1002번 학생 수정 : " + cnt);
		resultSt = session.selectOne("student.selectNo", 1002);
		System.out.println(resultSt);
		cnt = session.delete("student.delete" , 1002);
		System.out.println("student 테이블 1002번 학생 삭제 : " + cnt);
		resultSt = session.selectOne("student.selectNo", 1002);
		System.out.println(resultSt);
	}
}
