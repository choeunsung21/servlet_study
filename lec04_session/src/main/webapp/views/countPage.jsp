<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		<%!int visitCount = 1;%>
		<%
		Cookie c = new Cookie("visit_count", visitCount+"");
		c.setMaxAge(60 * 60 * 24);
		response.addCookie(c);
		%>
		당신은 이 페이지를 <strong><%=visitCount++%></strong>번 방문했습니다.
	</p>
</body>
</html>