package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.DeptVO;

@Repository("dept_dao")//dept_dao : 현재 DeptDAO를 자동생성하기 위한 별칭
public class DeptDAO {

	@Autowired
	SqlSession sqlSession;
	
	public DeptDAO() {
		System.out.println("--DeptDAO의 생성자--");
	}
	
	public List<DeptVO> selectList(){
		List<DeptVO> list = sqlSession.selectList("d.dept_list");
		return list;
	}
	
}














