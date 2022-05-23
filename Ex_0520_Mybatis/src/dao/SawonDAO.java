package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVO;

public class SawonDAO {
	SqlSessionFactory factory;
	
	public SawonDAO() {
		factory = MyBatisConnector.getInstance().getFactory();
		
	}
	
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static SawonDAO single = null;

	public static SawonDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new SawonDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	
	//사원목록 조회
	public List<SawonVO> select(){
	
		SqlSession sqlSession  = factory.openSession();
		
		List<SawonVO> list = sqlSession.selectList("sawon.sawon_list");
		
		//conn, pstmt, rs를 close()하는 내용이 포함되어 있다.
		//그러므로 사용 후에는 반드시 닫아줘야 한다.
		sqlSession.close();
		return list;
	}
	
	//부서별 사원 목록
	public List<SawonVO> select(int deptno){
		
		SqlSession sqlSession = factory.openSession();
		
		//sqlSession이 관리하는 모든 CRUD관련 메서드는 파라미터를 추가할 수 있다.
		//파라미터는 단 한 개만 추가할 수 있다.
		List<SawonVO> list = sqlSession.selectList("sawon.sawon_list_no", deptno);
		
		sqlSession.close();
		return list;
	}
}








