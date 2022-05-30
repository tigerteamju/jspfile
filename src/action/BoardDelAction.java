package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardDelAction
 */
@WebServlet("/del.do")
public class BoardDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//del.do?idx=35
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//삭제하고자 하는 원본게시물의 정보를 얻어온다
		BoardVO basevo = dao.selectOne(idx);
		
		basevo.setSubject("이미 삭제된 글입니다");
		basevo.setName("unknown");
		
		int res = dao.del_update(basevo);
		
		if( res > 0) {
			response.getWriter().print("[{'result':'yes'}]");
		}else {
			response.getWriter().print("[{'result':'no'}]");
		}
	}

}
