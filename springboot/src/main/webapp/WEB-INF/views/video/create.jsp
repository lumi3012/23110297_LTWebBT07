<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Danh sách Video</title>
</head>
<body>
	<div class="form-box">
		<h2 class="title">Thêm Video</h2>
		<form action="${pageContext.request.contextPath}/video/create"
			method="post" enctype="multipart/form-data">
			<input type="text" name="title" placeholder="Tên video" required>
			<textarea name="description" placeholder="Mô tả"></textarea>
			<input type="text" name="genre" placeholder="Thể loại" required>

			<label>Danh mục:</label> <select name="categoryId" required>
				<c:forEach var="c" items="${categories}">
					<option value="${c.id}">${c.name}</option>
				</c:forEach>
			</select> <label>Thumbnail:</label> <input type="file" name="thumbnail"
				accept="image/*"> <label>File video:</label> <input
				type="file" name="videoFile" accept="video/*" required> <label>Trạng
				thái:</label> <select name="active">
				<option value="true">Kích hoạt</option>
				<option value="false">Không kích hoạt</option>
			</select>

			<button type="submit" class="btn">Lưu</button>
		</form>
	</div>

</body>
</html>
