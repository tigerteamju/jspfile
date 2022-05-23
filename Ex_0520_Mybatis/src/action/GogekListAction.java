package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GogekDAO;
import vo.GogekVO;

/**
 * Servlet implementation class GogekListAction
 */
@WebServlet("/gogek.do")
public class GogekListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//gogek.do <--null
		//gogek.do?search= <-- empty
		//gogek.do?search=서울
		
		String search = "all";
		String str_search = request.getParameter("search");
		
		//
		if( str_search != null && !str_search.isEmpty()) {
			search = str_search;			
		}
		
			
		//고객목록 조회
		List<GogekVO> list = null;
		
		if( search.equals("all")) {
			
			list = GogekDAO.getInstance().select();
		}else {			
			//검색어와 일치하는 지역 검색
			list = GogekDAO.getInstance().select( search ); 
		}
		
		
		request.setAttribute("list", list);
		RequestDispatcher disp = request.getRequestDispatcher("gogek_list.jsp");
		disp.forward(request, response);
	}

}
