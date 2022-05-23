package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/login.do")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//login.do?id=one&pwd=1111
		request.setCharacterEncoding("utf-8");
		
		System.out.println("yes");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO vo = MemberDAO.getInstance().selectOne(id);
		
		String param = "";
		String resultStr= "";
		
		//vo가 null인 경우 id자체가 db에 존재하지 않는다는 의미
		if( vo == null) {
			param = "no_id";
			resultStr= String.format("[{'param':'%s'}]", param);
			response.getWriter().print(resultStr);
			return;
		}
		
		//vo에 갖고온 값 이랑     입력한 값 비교
		if( !vo.getPwd().equals(pwd) ) {
			//비밀번호가 일치하지 않을 때
			param="no_pwd";
			resultStr= String.format("[{'param':'%s'}]", param);
			response.getWriter().print(resultStr);
			return;
			
		}
		
		//아이디와 비밀번호 체크에 문제가 없다면 세션에 바인딩 한다
		//세션은 서버의 메모리를 사용하기 때문에 세션을 많이 사용할수록 브라우저가 느려지기 때문에
		//필요한 곳에서만 세션을 쓰도록 하자
		
		//세션유지시간(기본값30분)
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 60);//세션이 1시간 유지
		session.setAttribute("vo", vo);
		
		//아이디랑 비번 다 맞으면 clear
		param="clear";
		resultStr= String.format("[{'param':'%s'}]", param);
		response.getWriter().print(resultStr);
		 
	}
}
