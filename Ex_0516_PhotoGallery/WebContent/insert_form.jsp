<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진등록 페이지</title>

	<script>
		function send(f){
			var title= f.title.value.trim();
			var pwd = f.pwd.value;
			var photo = f.photo.value;
			
			if(title ==''){
				alert('제목을 입력해야합니다');
				return;
			}
			
			f.submit(); //insert.do로 파라미터를 가지고 화면을 전환
		}
		
	
	</script>
</head>
<body>
	<form 	action="insert.do"
			method="POST" 
			enctype="multipart/form-data">
		
		<table border="1" align="center">
			<caption>사진등록하기</caption>
			<tr>
				<th>제목</th>
				<td><input name="title"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="pwd" type="password"></td>
			</tr>
			<tr>
				<th>등록할 사진</th>
				<td><input name="photo" type="file"></td>
			</tr>	
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="등록하기" onclick="send(this.form);">
					<input type="button" value="목록으로"
							 onclick="location.href='list.do'">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>





