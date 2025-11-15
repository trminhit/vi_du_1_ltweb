<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.account}">
    <%
        response.sendRedirect(request.getContextPath() + "/login");
    %>
</c:if>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>

	<h2>Hello World, Nguyễn Trường Minh with HCMUTE</h2>
		
	<a href="logout">Đăng xuất</a>

</body>
</html>