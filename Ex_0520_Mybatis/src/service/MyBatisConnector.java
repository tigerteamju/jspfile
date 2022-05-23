package service;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnector {
	//Mybatis는 데이터베이스 프로그래밍을 좀 더 쉽고 간단하게 할 수 있도록 도와주는 프레임워크
	
	SqlSessionFactory factory = null;
	
	
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static MyBatisConnector single = null;

	public static MyBatisConnector getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new MyBatisConnector();
		//생성된 객체정보를 반환
		return single;
	}
	
	public MyBatisConnector() {
		//sqlMapConfig.xml읽어온다
		try {
			Reader reader = Resources.getResourceAsReader(
								"config/mybatis/sqlMapConfig.xml");
			
			//위에서 읽어온 sqlMapConfig.xml에서 지정해둔 DB접근 경로를
			//실제로 읽어온다
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//sqlMapConfig.xml의 정보를 담고 있는 Factory객체를 반환
	public SqlSessionFactory getFactory() {
		return factory;
	}
	
	
	
	
	
	
}
