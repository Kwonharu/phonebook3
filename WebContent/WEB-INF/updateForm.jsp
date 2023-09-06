<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
<%@ page import="com.javaex.dao.PersonDao" %>
    
<%

	PersonVo personVo = (PersonVo)request.getAttribute("personVo");	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>"<%=personVo.getName()%>" 전화번호 수정</h1>
	<h2>수정폼</h2>
	
	<p>정보를 수정하는 폼입니다. 정보를 수정하고 수정 버튼을 누르세요.</p>

	<form action="/phonebook3/PhonebookController" method="get">
		이름(name): <input type="text" name="name" value="<%=personVo.getName()%>"><br>
		핸드폰(hp): <input type="text" name="hp" value="<%=personVo.getHp()%>"><br>
		회사(company): <input type="text" name="company" value="<%=personVo.getCompany()%>"><br>
			<input type="hidden" name="id" value="<%=personVo.getPerson_id()%>"><br>
		<input type="hidden" name="action" value="update"><br>
		
		<button type="submit">수정</button>
	</form>
	
	<br>
	<br>
	
	<a href="/phonebook3/PhonebookController?action=list">리스트로 바로가기</a>
	
</body>
</html>


