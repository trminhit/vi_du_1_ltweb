<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Danh mục</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-4">

	<h2>Chỉnh sửa Danh mục</h2>
	
	<c:url value="/admin/category/edit" var="editURL" />
	
	<form action="${editURL}" method="post">
		
		<input type="hidden" name="id" value="${category.cateid}">
		
		
		<div class="mb-3">
			<label for="catename" class="form-label">Tên danh mục:</label>
			
			<input type="text" class="form-control" id="catename"
				name="catename" value="${category.catename}" required>
		</div>

		<button type="submit" class="btn btn-success">Cập nhật</button>
		<a href="<c:url value='/admin/category/list' />" class="btn btn-secondary">Hủy</a>
	</form>

</body>
</html>