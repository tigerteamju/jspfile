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
 * Servlet implementation class BoardReplyAction
 */
@WebServlet("/reply.do")
public class BoardReplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		int idx= Integer.parseInt(request.getParameter("idx"));		
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//기준 글의 idx를 사용해서 댓글을 달고 싶은 원글 정보를 먼저 얻어온다.
		BoardVO base_vo = dao.selectOne(idx);

		//이미 달린 댓글들 밀기(공간확보)
		//기준 글의 step이상인 값은 step = step + 1처리 
		dao.update_step(base_vo);
		
		//댓글VO
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setIp(ip);
		//댓글이 들어갈 위치 선정
		vo.setRef(base_vo.getRef());
		vo.setStep(base_vo.getStep() + 1);
		vo.setDepth(base_vo.getDepth() + 1);
		
		//댓글 등록
		int res = dao.reply(vo);
		
		if(res > 0) {
			int page = Integer.parseInt(request.getParameter("page"));
			response.sendRedirect("list.do?page="+page);
			
		}
	}

}
