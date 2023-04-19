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

public class main1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			//xml 파일 읽어서 준비
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			//sqlMap : sql 구무들을 id속성값으로 저장하는 객체(ex. member.count)
			sqlMap = new SqlSessionFactoryBuilder().build(reader); //xml 파일 읽은 reader로 sqlsessionfactory를 build
		} catch(IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		SqlSession session = sqlMap.openSession(); //openSession() : disconnect => connect, session=접속된 객체(Connection 객체를 mybatis에서 연결한 객체)
		//selectOne : 결과 레코드가 한건인 경우.(2개 이상 안됨)
		x = (Integer)session.selectOne("member.count");
		System.out.println("member 테이블의 레코드 개수 : " + x);
		System.out.println("member 테이블 전체 조회 ===========");
		//selectList : 결과 레코드가 여러건인 경우 List로 리턴.
		List<Member> list = session.selectList("member.list");
		for(Member m : list) System.out.println(m);
		System.out.println("member 테이블 admin 정보 조회 ===========");
		Member mem = session.selectOne("member.selectId","admin");
		System.out.println(mem);
		
		System.out.println("member 테이블 이름에 '홍'을 가진 정보 조회 ===========");
		list = session.selectList("member.selectName","%홍%");
		for(Member m : list) System.out.println(m);
		System.out.println("member 테이블 이름에 '홍'을 가진 정보 조회2 ===========");
		list = session.selectList("member.selectName2","홍");
		for(Member m : list) System.out.println(m);
		Map<String, Object> map = new HashMap<>();
		
		//파라미터 2개 이상일 때, Map 객체 이용
		System.out.println("member 테이블 이름에 '멍'을 가진 남자 정보 조회 ===========");
		map.put("name", '멍');
		map.put("gender", 1);		
		list = session.selectList("member.selectNameGender",map);
		for(Member m : list) System.out.println(m);
	}
}
