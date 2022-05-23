package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.UserDAO;
import vo.UserVO;

/**
 * Servlet implementation class MemberUpdateAction
 */
@WebServlet("/member_modi.do")
public class MemberUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		UserVO vo = new UserVO();
		vo.setIdx(idx);
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		
		int res = UserDAO.getInstance().update(vo);
		
		String param="";
		
		if(res > 0 ) {
			param = "yes";
			response.getWriter().print(param);
		}
	}

}
