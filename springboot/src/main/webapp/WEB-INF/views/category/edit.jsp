<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Sửa Category</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="form-box">
    <h2>Sửa danh mục</h2>
    <form action="${pageContext.request.contextPath}/category/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${cate.id}">
        
        <input type="text" name="name" value="${cate.name}" placeholder="Tên danh mục" required>
        <textarea name="description" placeholder="Mô tả">${cate.description}</textarea>
        <input type="text" name="type" value="${cate.type}" placeholder="Loại">
        
        <label><input type="checkbox" name="active" value="true" <c:if test="${cate.active}">checked</c:if>> Kích hoạt</label>
        
        <p>Icon hiện tại:</p>
        <c:if test="${cate.icon != null}">
            <img src="${pageContext.request.contextPath}/${cate.icon}" width="40"><br>
        </c:if>
        <input type="file" name="icon">
        
        <p>Ảnh hiện tại:</p>
        <c:if test="${cate.image != null}">
            <img src="${pageContext.request.contextPath}/${cate.image}" width="80"><br>
        </c:if>
        <input type="file" name="image">

        <button type="submit">Cập nhật</button>
    </form>
</div>

<div style="text-align:center; margin-top:15px;">
    <a href="${pageContext.request.contextPath}/category" class="btn">← Quay lại danh sách</a>
    <a href="${pageContext.request.contextPath}/home" class="btn">🏠 Về trang chủ</a>
</div>
</body>
</html>
