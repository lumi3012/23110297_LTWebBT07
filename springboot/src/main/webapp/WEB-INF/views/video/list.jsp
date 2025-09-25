<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Video</title>
</head>
<body>
<h2 style="color:#2e7d32; text-align:center;">Danh sách Video</h2>
<form method="get" action="${pageContext.request.contextPath}/video" style="text-align:center; margin-bottom:15px;">
    <input type="text" name="q" placeholder="Tìm video..." style="padding:5px; border-radius:5px;">
    <button type="submit" class="btn">Tìm</button>
    <a href="${pageContext.request.contextPath}/video/create" class="btn">+ Thêm mới</a>
</form>
<div class="grid">
    <c:forEach var="v" items="${videos}">
        <div class="card">
            <img src="${v.thumbnail}" alt="${v.title}" style="width:100%; height:150px; object-fit:cover; border-radius:8px;">
            <h3>${v.title}</h3>
            <p>${v.category.name}</p>
            <a href="${pageContext.request.contextPath}/video/detail?id=${v.id}" class="btn">Xem</a>
            <a href="${pageContext.request.contextPath}/video/edit?id=${v.id}" class="btn">Sửa</a>
            <a href="${pageContext.request.contextPath}/video/delete?id=${v.id}" class="btn danger">Xóa</a>
        </div>
    </c:forEach>
</div>
<div style="text-align:center; margin-top:15px;">
    <a href="${pageContext.request.contextPath}/home" class="btn">← Về trang chủ</a>
</div>

</body>
</html>
