<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script>
		function find(){
			//select태그의 value값 (총무부가 선택됐다면 value=10)을 가져온다
			var deptno= document.getElementById("deptno").value;
			location.href="sawon.do?deptno="+deptno;
		}
	
	</script>
	
</head>
<body>

	<div align="center">
		<select id="deptno">
			<option value="0">모든 부서 보기</option>
			<option value="10">총무부</option>
			<option value="20">영업부</option>
			<option value="30">전산실</option>
			<option value="40">관리부</option>
			<option value="50">경리부</option>
		</select>
		
		<input type="button" value="검색" onclick="find();">
	</div>
	<table border="1">
		<caption>사원목록</caption>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>직책</th>
			<th>급여</th>
			<th>입사일</th>
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.sabun }</td>
				<td>${vo.saname }</td>
				<td>${vo.sajob }</td>
				<td>${vo.sapay }</td>
				<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>				
				<td>${fn:split(vo.sahire, ' ')[0]}</td>
			</tr>	
		</c:forEach>
	</table>
</body>
</html>