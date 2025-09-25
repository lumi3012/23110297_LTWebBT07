<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Danh sách Category</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<style>
table {
	border-collapse: collapse;
	width: 90%;
	margin: 0 auto;
	text-align: center;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
}

th {
	background: #2e7d32;
	color: white;
}

.btn {
	padding: 5px 10px;
	border-radius: 5px;
	background: #43a047;
	color: white;
	text-decoration: none;
	margin: 2px;
}

.btn:hover {
	background: #2e7d32;
}

.btn.danger {
	background: #e53935;
}

.btn.danger:hover {
	background: #b71c1c;
}
</style>
</head>
<body>
	<h2 style="color: #2e7d32; text-align: center;">Quản lý Danh mục</h2>

	<form method="get" action="${pageContext.request.contextPath}/category"
		style="text-align: center; margin-bottom: 15px;">
		<input type="text" name="q" placeholder="Tìm danh mục..."
			style="padding: 5px; border-radius: 5px;">
		<button type="submit" class="btn">Tìm</button>
		<a href="${pageContext.request.contextPath}/category/create"
			class="btn">+ Thêm mới</a>
	</form>

	<table>
		<tr>
			<th>ID</th>
			<th>Icon</th>
			<th>Tên</th>
			<th>Mô tả</th>
			<th>Hình ảnh</th>
			<th>Thao tác</th>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
				<td>${c.id}</td>
				<td><c:if test="${c.icon != null}">
						<img src="${pageContext.request.contextPath}/${c.icon}"
							style="width: 30px; height: 30px;">
					</c:if></td>
				<td>${c.name}</td>
				<td>${c.description}</td>
				<td><c:if test="${c.image != null}">
						<img src="${pageContext.request.contextPath}/${c.image}"
							style="width: 60px; height: 40px;">
					</c:if></td>
				<td><a href="${pageContext.request.contextPath}/category/edit?id=${c.id}" class="btn">Sửa</a>

					<form action="${pageContext.request.contextPath}/category/delete"
						method="post" style="display: inline;">
						<input type="hidden" name="id" value="${c.id}">
						<button type="submit" class="btn danger"
							onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align:center; margin-top:15px;">
    <a href="${pageContext.request.contextPath}/home" class="btn">← Về trang chủ</a>
</div>
	
</body>
</html>
