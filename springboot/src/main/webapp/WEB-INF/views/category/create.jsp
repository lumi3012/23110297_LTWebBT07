<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f0f4f0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .form-box {
        background: #fff;
        padding: 30px 25px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0,0,0,0.2);
        width: 450px;
        text-align: center;
    }
    .form-box h2 {
        color: #2e7d32;
        margin-bottom: 20px;
    }
    .form-box input[type="text"],
    .form-box textarea,
    .form-box input[type="file"],
    .form-box select {
        width: 100%;
        padding: 10px;
        margin: 8px 0;
        border: 1px solid #ccc;
        border-radius: 6px;
        outline: none;
    }
    .form-box input:focus,
    .form-box textarea:focus {
        border-color: #2e7d32;
        box-shadow: 0 0 5px rgba(46,125,50,0.5);
    }
    .form-box button {
        width: 100%;
        padding: 10px;
        margin-top: 12px;
        border: none;
        background: #43a047;
        color: #fff;
        font-size: 16px;
        border-radius: 6px;
        cursor: pointer;
        transition: 0.3s;
    }
    .form-box button:hover {
        background: #2e7d32;
    }
</style>
</head>
<body>
<div class="form-box">
    <h2>Thêm danh mục</h2>
    <form action="${pageContext.request.contextPath}/category/create" 
          method="post" enctype="multipart/form-data">

        <input type="text" name="name" placeholder="Tên danh mục" required>
        <textarea name="description" placeholder="Mô tả"></textarea>

        <input type="text" name="type" placeholder="Loại">

        <label style="display:block; margin-top:10px; text-align:left;">
            <input type="checkbox" name="active" value="true"> Kích hoạt
        </label>

        <label style="display:block; margin-top:10px; text-align:left;">Icon:</label>
        <input type="file" name="icon">

        <label style="display:block; margin-top:10px; text-align:left;">Hình ảnh:</label>
        <input type="file" name="image">

        <button type="submit">Lưu danh mục</button>
    </form>
</div>
</body>
</html>
