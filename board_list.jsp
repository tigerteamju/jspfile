<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
		a{text-decoration: none;}
		table{border-collapse: collapse;}
	</style>
</head>
<body>
	<table border="1" width="700">
		<tr>
			<td colspan="5"><img src="img/title_04.gif"></td>
		</tr>
		<tr>
			<th width>번호</th>
			<th width="350">제목</th>
			<th width="120">작성자</th>
			<th width="100">작성일</th>
			<th width="50">조회수</th>	
		</tr>
		
		<!-- 게시물들을 보여줄 forEach -->
		<c:forEach var="vo" items="${ list }">
			<tr>
				<td align="center">${vo.idx }</td>
				
				<td>
				<!-- 댓글일 경우 들여쓰기 -->
				<c:forEach begin="1" end="${ vo.depth }">&nbsp;</c:forEach>					
				<!-- 댓글기호 -->			
				<c:if test="${ vo.depth ne 0 }">ㄴ</c:if>
				
				<!-- 삭제되지 않은 글일 경우 클릭이 가능 / del_info가 -1이 아닐 때-->
				<c:if test="${vo.del_info ne -1 }">
					<a href="view.do?idx=${vo.idx }&page=${param.page}">
					<font color="black">${vo.subject }</font>
					</a>
				</c:if>
				
				<!-- 삭제가 된 글일 경우 클릭이 불가능 -->
				<c:if test="${vo.del_info eq -1 }">
					<font color="gray">${vo.subject }</font>
					
				</c:if>
				</td>
				
				<td align="center">${vo.name }</td>
				
				<!-- 삭제가 되지 않은 게시물은 등록일자를 정상적으로 표기 -->
				<c:if test="${vo.del_info ne -1 }">
				<td align="center">${ fn:split(vo.regidate, ' ')[0] }</td>
				</c:if>
				
				<!-- 삭제가 된 글일 경우 클릭이 불가능 -->
				<c:if test="${vo.del_info eq -1 }">
				<td align="center">unknown</td></c:if>
				
				<td align="center">${vo.readhit }</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="5" align="center">
				${pageMenu }
			</td>
		</tr>
		
		<tr>
			<td colspan="5" align="right">
				<img src="img/btn_reg.gif" 
					onclick="location.href='insert_form.jsp'"
					style="cursor:pointer;">
			</td>
		</tr>
	</table>
</body>
</html>