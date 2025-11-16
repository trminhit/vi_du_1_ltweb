<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chào mừng</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet">
</head>
<body class="container mt-5 text-center">

    <h2>Chào mừng đến với Trang Quản lý Sản phẩm</h2>
    <p>Vui lòng đăng nhập để tiếp tục.</p>
    <a href="<c:url value='/login' />" class="btn btn-primary btn-lg">Đăng nhập</a>

</body>
</html>