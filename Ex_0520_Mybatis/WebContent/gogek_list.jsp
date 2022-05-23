<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script>
		function search(){
			var search = document.getElementById("search").value;
			location.href = "gogek.do?search="+search;
			
		}
	</script>
</head>
<body>
<table border="1">
		<caption>부서목록</caption>
		<div>
			<input id="search" placeholder="검색어를 입력하세요">
			<input type="button" value="검색" onclick="search();">
		</div>
		<tr>
			<th>고객번호</th>
			<th>고객 이름</th>
			<th>고객 주소</th>
			<th>고객주민번호</th>
			<th>담당자 번호</th>
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.gobun }</td>
				<td>${vo.goname }</td>
				<td>${vo.goaddr}</td>
				<td>${vo.gojumin}</td>
				<td>${vo.godam}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>