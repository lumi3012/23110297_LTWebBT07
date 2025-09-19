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
<table style="width:90%; margin:0 auto; border-collapse:collapse; text-align:center;">
    <tr style="background:#2e7d32; color:#fff;">
        <th>ID</th>
        <th>Thumbnail</th>
        <th>Tên</th>
        <th>Mô tả</th>
        <th>Thể loại</th>
        <th>Danh mục</th>
        <th>Hoạt động</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="v" items="${videos}">
        <tr>
            <td>${v.id}</td>
            <td>
                <a href="${pageContext.request.contextPath}/video/detail?id=${v.id}">
                    <img src="${v.thumbnail}" alt="${v.title}" style="width:60px; height:40px; object-fit:cover; border-radius:4px;">
                </a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/video/detail?id=${v.id}" style="color:#2e7d32; text-decoration:underline;">${v.title}</a>
            </td>
            <td>${v.description}</td>
            <td>${v.genre}</td>
            <td>${v.category.name}</td>
            <td>
                <c:choose>
                    <c:when test="${v.active}"><span style="color:green; font-weight:bold;">Hoạt động</span></c:when>
                    <c:otherwise><span style="color:red; font-weight:bold;">Ẩn</span></c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/video/edit?id=${v.id}" class="btn">Sửa</a>
                <a href="${pageContext.request.contextPath}/video/delete?id=${v.id}" class="btn danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div style="text-align:center; margin-top:15px;">
    <a href="${pageContext.request.contextPath}/home" class="btn">← Về trang chủ</a>
</div>

</body>
</html>