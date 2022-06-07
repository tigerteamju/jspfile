package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDAO;
import vo.DeptVO;

@Controller
public class TestController {
	
	
	@Autowired
	DeptDAO dept_dao; //@Repository("dept_dao") -> 객체이름과 별칭을 같게 해 준다
	
	public TestController() {
		System.out.println("--TestController의 생성자--");
	}
	
	@RequestMapping(value= {"/", "/list.do"})
	public String deptList( Model model ) {
		
		List<DeptVO> dept_list = dept_dao.selectList();
		model.addAttribute("list", dept_list);
		return "/WEB-INF/views/dept_list.jsp";
		
	}
	
}











