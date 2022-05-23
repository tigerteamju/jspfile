package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDAO;
import vo.PhotoVO;

/**
 * Servlet implementation class PhotolistAction
 */
@WebServlet("/list.do")
public class PhotolistAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DAO로 접근하여 photo전체목록을 조회
		List<PhotoVO> list = PhotoDAO.getInstance().selectList();
		
		//list를 스코프 영역에 저장(바인딩)
		request.setAttribute("list", list);
		
		//바인딩 된 정보를 가지고 출력할 페이지를 지정(포워딩)
		RequestDispatcher disp =
				request.getRequestDispatcher("photo_list.jsp");
		
		disp.forward(request, response);
		
	}

}














