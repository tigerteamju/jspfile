package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SawonDAO;
import vo.SawonVO;

/**
 * Servlet implementation class SawonListAction
 */
@WebServlet("/sawon.do")
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sawon.do?deptno=10
		int deptno = 0;
		//값이 없는지 확인하기 위해서 스트링으로 받기
		String str_deptno = request.getParameter("deptno");
		
		//sawon.do? <--null
		//sawon.do?deptno=
		if( str_deptno != null && !str_deptno.isEmpty() ){
			//파라미터가 없거나 비어있는 상태가 아닌 정상적으로 전달되었다면
			//실제 정수로 바꿔준다
			deptno = Integer.parseInt(str_deptno);
			
		}
		
		List<SawonVO> list = null;
		
		// 사원목록 조회
		if(deptno == 0 ){ //전체목록 조회
		
			list = SawonDAO.getInstance().select(); 
			
		}else{
			//파라미터에 해당하는 부서별 사원 조회
			list = SawonDAO.getInstance().select(deptno);
		}
		
		request.setAttribute("list", list);
		
		RequestDispatcher disp = request.getRequestDispatcher("sawon_list.jsp");
		disp.forward(request, response);
	}

}
