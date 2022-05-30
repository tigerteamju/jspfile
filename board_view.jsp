<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- Ajax사용을 위한 js참조 -->
	<script src="js/httpRequest.js"></script>
	
	<script>
		function reply(){
			location.href="replyForm.jsp?idx=${vo.idx}&page=${param.page}";
			
		}
		
		function del(){
			if( !confirm("삭제하시겠습니까?")){
				return;
				
			}
			
			var pwd = '${vo.pwd}';//원본비번
			var c_pwd = document.getElementById("c_pwd").value;
			
			if( pwd!= c_pwd){
				alert("비밀번호 불일치");
				return;
			}
			
			//Ajax
			var url = "del.do";
			var param = "idx=${vo.idx}";
			sendRequest(url ,param , delCheck, "post");
		}
		
		//삭제 여부를 확인하는 콜백
		function delCheck(){
			if( xhr.readyState == 4 && xhr.status == 200){
				
				var data = xhr.responseText;
				var json = eval(data);
				
				if( json[0].result == 'yes'){
					alert("삭제성공");
					location.href="list.do?page=${param.page}";
				}else{
					alert("삭제실패");
					
				}
				
			}
			
		}
	</script>
</head>
<body>

	<table border="1">
		<caption>:::게시글 상세보기 :::</caption>
		<tr>
			<th>제목</th>
			<td>${vo.subject }</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>${vo.name }</td>
		</tr>
		
		<tr>
			<th>작성일</th>
			<td>${vo.regidate }</td>
		</tr>
		
		<tr>
			<th>ip</th>
			<td>${vo.ip }</td>
		</tr>
		
		
		<tr>
			<th>내용</th>
			<td width="500" height="200"><pre>${vo.content }</pre></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="c_pwd"></td>
			
		</tr>
		<tr>
			<td colspan="2">
			<!-- 목록보기 -->
				<img src="img/btn_list.gif" style="cursor:pointer;"
				onclick="location.href='list.do?page=${param.page}'">
				
				<!-- 원글일 때만 답변 버튼 생김 (대댓글 방지) -->
				<c:if test="${vo.depth lt 1 }">  
					<img src="img/btn_reply.gif" style="cursor:pointer;"
				 	onclick="reply();">
				</c:if>
				
				
				<!-- 글 삭제 -->
				<img src="img/btn_delete.gif" style="cursor:pointer;"
				onclick="del();">
			</td>
		</tr>
	</table>
</body>
</html>










