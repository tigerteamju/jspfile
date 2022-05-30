<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 등록하기</title>

	<script>
		function send_check(){
			
			var f = document.f; //f라는 name을 가진 form태그를 가져옴
			
			var name = f.name.value;
			//유효성 체크 했다쳐
			
			f.submit();
		
		}
		
		
	 
	</script>
</head>
<body>
	<form name="f"
		  method="post"
		  action="insert.do">
			
		<table border="1">
			<caption>:::새 글 쓰기:::</caption>
			
			<tr>
				<th>제목</th>
				<td><input name="subject" style="width:380px;"></td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td><input name="name" style="width:380px;"></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="50"
					style="resize:none;"></textarea></td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><input name="pwd" type="password" style="width:380px;"></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<img src="img/btn_reg.gif"
					style="cursor:pointer;" onclick="send_check();">
					
					<img src="img/btn_back.gif" 
					style="cursor:pointer;" onclick="location.href='list.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>