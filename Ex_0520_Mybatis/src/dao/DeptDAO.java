package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVO;


public class DeptDAO {
	SqlSessionFactory factory;
	
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static DeptDAO single = null;

	public static DeptDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new DeptDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	public DeptDAO() {
		factory = MyBatisConnector.getInstance().getFactory();
		
	}
	
	//부서테이블 조회
	public List<DeptVO> select(){
		
		//SqlSession : DB에 쿼리문을 요청하는 객체
		SqlSession sqlSession = factory.openSession();
		
		//DB접근을 통해 얻어온 결과를 list에 저장	
		List<DeptVO> list = sqlSession.selectList("dept.dept_list");
		
		//DB접근을 위해 사용한 sqlSession은 마지막에 꼭 닫아줄 것!
		sqlSession.close();
		
		return list;
	}
}









