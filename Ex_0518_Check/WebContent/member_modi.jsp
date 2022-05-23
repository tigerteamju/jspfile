<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");

	int idx = Integer.parseInt(request.getParameter("idx"));
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script>
		function update( f ){
			var idx = f.idx.value;
			var name = f.name.value.trim();
			var id = f.id.value.trim();
			var pwd = f.pwd.value.trim();
			
			var url = "member_modi.do"
			var param = "idx="+ idx +"&name=" + encodeURIComponent(name)+
			"&id="+ encodeURIComponent(id) + "&pwd=" + encodeURIComponent(pwd);
			
			sendRequest(url, param, resultFn, "post");
			
		}
		function resultFn(){
			
			if(xhr.readyState == 4 && xhr.status == 200){
				
				var data = xhr.responseText; // "yes" or "no"
				if( data == "yes"){
					alert("삭제성공");
				}else{
					alert("삭제실패");
				}
				location.href="member_list.do";
			}		
			}
		
	
	</script>
</head>
<body>
	<form>
	<input type="hidden" name="idx" value=<%=idx %>>
	<table border="1">
		<caption>회원정보 수정</caption>
		
		<tr>
			<th>이름</th>
			<td><input name="name" value= "<%=name %>" ></td>
		</tr>
	
		<tr>
			<th>아이디</th>
			<td><input name="id" value= "<%=id %>" ></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input name="pwd" value= "<%=pwd %>" ></td>
		</tr>
		<tr>
					<td colspan="2">
						<input type="button" value="수정"
						onclick="update(this.form);">
						
					</td>
		</tr>
	</table>
		
	</form>
</body>
</html>