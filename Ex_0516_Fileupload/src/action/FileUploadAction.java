package action;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadAction
 */
//@WebServlet : url매핑 - jsp등에서 현재 서블릿을 요청할 수 있는 식별자를 지정하는 영역

@WebServlet("/upload.do")
public class FileUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, //요청처리객체
							HttpServletResponse response) //응답처리객체
									throws ServletException, IOException {
		//매핑이 호출되면 가장 먼저 실행되는 서비스 메서드
		System.out.println("서블릿접속됨");
		
//		request.setCharacterEncoding("utf-8");
		
		//파일업로드( multipart/form-data )
		String web_path = "/upload/";
		
		//현재 프로젝트에 대한 정보를 관리하는 클래스
		ServletContext application = request.getServletContext();
		
		//upload폴더까지의 절대경로(상대경로에는 표시안됨)
		String path = application.getRealPath(web_path);
		System.out.println(path);
		
		int max_size = 1024 * 1024 * 100; //최대업로드 용량 100mb
		
		//파일을 포함한 파라미터를 수신하기 위한 객체
		MultipartRequest mr = new MultipartRequest(
					request, //request정보를 위임
					path, //업로드 경로
					max_size, //최대 업로드 용량
					"utf-8", //수신시 인코딩타입
					new DefaultFileRenamePolicy()); //중복된 파일명을 알아서 변경
		
		String filename = "no_file";
		
		//업로드가 완료된 파일의 정보를 얻어오자
		File f = mr.getFile("photo");
		
		if( f != null ) {
			filename = f.getName();//업로드된 파일의 이름을 가져올 수 있다.
		}
		
		//파일 이외의 일반 파라미터
		String title = mr.getParameter("title");
		
		//파일명과 제목을 request영역에 저장(바인딩)
		request.setAttribute("title", title);
		request.setAttribute("filename", filename);
		
		//바인딩 해놓은 정보를 어떤 페이지에서 가져다가 사용할 것이니즐 지정(포워딩)
		RequestDispatcher disp = request.getRequestDispatcher("result.jsp");
		disp.forward(request, response);
		
	}

}








