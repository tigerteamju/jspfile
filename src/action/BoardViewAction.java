package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import vo.BoardVO;


@WebServlet("/view.do")
public class BoardViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//view.do?idx=3
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		 BoardDAO dao = BoardDAO.getInstance();
		 
		 //idx에 해당하는 게시글 한건 상세보기
		 BoardVO vo = dao.selectOne(idx);
		
		 //조회수 증가
		 HttpSession session = request.getSession();
		 String show = (String)session.getAttribute("show");
		 
		 if( show == null) {			 
			 int res = dao.update_readhit(idx);
			 session.setAttribute("show", "0");
		 }
		 
		 //상세보기페이지로 전환하기 위해 바인딩 및 포워딩
		 request.setAttribute("vo", vo);
		 RequestDispatcher disp = request.getRequestDispatcher("board_view.jsp");
		 disp.forward(request, response);
		 
		 
	}

}
