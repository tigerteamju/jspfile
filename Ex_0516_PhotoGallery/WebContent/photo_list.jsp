<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- 외부스타일시트 참조 -->
	<link rel="stylesheet" href="css/photo.css">
	
	<!-- Ajax사용을 위한 js등록 -->
	<script src="js/httpRequest.js"></script>
	
	<script>
		function del(f){

			var idx = f.idx.value;
			var pwd = f.pwd.value; //원본 비밀번호
			var pwd2 =f.pwd2.value; //지우기 위해 입력한 비번
			
			if(pwd != pwd2 ){
				alert('비밀번호가 일치하지 않습니다!');
				return;
			}
			
			if( !confirm("삭제?")){
				return;
			}
			
			//삭제를 원하는 idx를 서버로 전송
			var url = "photo_del.do";
			
			//encodeURIComponent: 전달할 파라미터에 특수문자가 섞여 있다면 
			//오류를 방지하기 위해 사용해주는 인코딩 메서드
			var param = "idx=" + encodeURIComponent( idx )+"&filename="+f.filename.value;
			
			sendRequest( url , param, finRes, "POST");
			//url로 param가지고 가서 finRes로 돌아오기 post방식으로 비밀번호니까
			
			function finRes(){
				if( xhr.readyState == 4 & xhr.status == 200){
					
					//서블릿으로부터 도착한 데이터 읽어오기
					var data = xhr.responseText;
					
					//넘겨받은 data는 ""로 묶여진 문자열 구조로 인식하기 때문에
					//JSON형식으로 변경을 해줘야 한다
					//data --> "[{'param':'no'}]"
					
					//문자열 형태의 JSON데이터를 실제 JSON으로 파싱
					var json = eval(data);
					
					if( json[0].param == 'yes'){
						alert('삭제 성공');
					}else{
						alert("삭제 실패");
						
					}
					
					location.href="list.do";
				}
			}
			
			
			
			
		}
	
		function download( fn ){
			location.href="download.do?dir=/upload/&filename="+fn;
		}
		
	</script>
</head>
<body>
	<div id="main_box">
		<h1>:::Photo Gallery:::</h1>
		
		<div align="center">
			<input type="button" value="사진등록"
					onclick="location.href='insert_form.jsp'">
		</div>
		
		<div id="photo_box">
			<c:forEach var="vo" items="${ list }">
				<div class="photo_type">
					<img src="upload/${vo.filename }">
					<div class="title">${vo.title }</div>
					
					<form>
						<input type="hidden" name="idx" value="${vo.idx }">
						<input type="hidden" name="pwd" value="${vo.pwd }">
						<input type="hidden" name ="filename" value="${vo.filename }">
						
						<div align="center">
							<input type="password" name="pwd2" size="5">
							<input type="button" value="down" 
										onclick="download('${vo.filename}');">
							<input type="button" value="삭제" onclick="del(this.form);">
						</div>	
					</form>		
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>