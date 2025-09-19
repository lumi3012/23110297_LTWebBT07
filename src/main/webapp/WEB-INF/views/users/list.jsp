<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý User</title>
</head>
<body>
	<div class="container">
		<h2 class="title">Quản lý User</h2>
		<form method="get" action="${pageContext.request.contextPath}/user"
			class="search-form">
			<input type="text" name="q" placeholder="Tìm user..."
				class="search-input">
			<button type="submit" class="btn">Tìm</button>
			<a href="${pageContext.request.contextPath}/user/create" class="btn">+
				Thêm mới</a>
		</form>

		<table class="data-table">
			<tr>
				<th>ID</th>
				<th>Avatar</th>
				<th>Username</th>
				<th>Fullname</th>
				<th>Email</th>
				<th>Role</th>
				<th>Thao tác</th>
			</tr>
			<c:forEach var="u" items="${users}">
				<tr>
					<td>${u.id}</td>
					<td><img src="${u.avatar}" class="avatar"></td>
					<td>${u.username}</td>
					<td>${u.fullname}</td>
					<td>${u.email}</td>
					<td><c:choose>
							<c:when test="${u.roleid == 1}">Admin</c:when>
							<c:when test="${u.roleid == 2}">Manager</c:when>
							<c:otherwise>User</c:otherwise>
						</c:choose></td>
					<td><a
						href="${pageContext.request.contextPath}/user/edit?id=${u.id}"
						class="btn">Sửa</a> <a
						href="${pageContext.request.contextPath}/user/delete?id=${u.id}"
						class="btn danger">Xóa</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="text-align: center; margin-top: 15px;">
		<a href="${pageContext.request.contextPath}/home" class="btn">← Về
			trang chủ</a>
	</div>

</body>
</html>
