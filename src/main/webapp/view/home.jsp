<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ User</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-5">
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h2>Chào mừng, ${sessionScope.account.username}!</h2>

		<a href="<c:url value='/logout' />" class="btn btn-danger">Đăng
			xuất</a>
	</div>

	<p>Đây là trang chủ dành cho User (Role = 2).</p>

</body>
</html>