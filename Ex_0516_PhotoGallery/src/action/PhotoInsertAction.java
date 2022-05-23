package action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDAO;
import vo.PhotoVO;

/**
 * Servlet implementation class PhotoInsertAction
 */
@WebServlet("/insert.do")
public class PhotoInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//insert.do?title=aaa&pwd=111&photo=^%$^
		String web_path="/upload/";
		ServletContext application = request.getServletContext();
		String path = application.getRealPath(web_path);
		System.out.println(path);//절대경로 확인
		
		int max_size = 1024*1024*100; //최대 업로드 용량 100mb
		MultipartRequest mr = new MultipartRequest(
				request, path, max_size, "utf-8", 
				new DefaultFileRenamePolicy());
		
		String filename="";
		File f = mr.getFile("photo");
		if( f != null) {
			filename = f.getName();//업로드 된 파일의 실제 파일명
			
		}
		
		//파일형식 이외의 나머지 파라미터 수신
		String title= mr.getParameter("title");
		String pwd = mr.getParameter("pwd");
		String ip = request.getRemoteAddr(); //접속자의 ip정보
		
		PhotoVO vo = new PhotoVO();
		vo.setTitle(title);
		vo.setFilename(filename);
		vo.setPwd(pwd);
		vo.setIp(ip);
		
		int res = PhotoDAO.getInstance().insert(vo);
		
		if( res >= 1) {
			response.sendRedirect("list.do");
		}
	}

}




