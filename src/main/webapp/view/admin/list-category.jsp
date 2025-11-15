<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Danh mục</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-4">

	<h2>Quản lý Danh mục sản phẩm</h2>

	<a href="<c:url value='/admin/category/add' />" class="btn btn-primary mb-3">
		Thêm danh mục mới
	</a>

	<table class="table table-bordered table-striped">
		<thead class="table-dark">
			<tr>
				<th>ID (hoặc STT)</th>
				<th>Tên danh mục</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${cateList}" var="cate" varStatus="STT">
				<tr>
					<td>${cate.cateid}</td>
					<td>${cate.catename}</td>
					
					<td>
						<a href="<c:url value='/admin/category/edit?id=${cate.cateid }'/>">Sửa</a>
						| 
						<a href="<c:url value='/admin/category/delete?id=${cate.cateid }'/>" 
						   onclick="return confirm('Bạn có chắc chắn muốn xóa không?')">Xóa</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

	<a href="<c:url value='/home' />">Quay về trang chủ</a>

</body>
</html>