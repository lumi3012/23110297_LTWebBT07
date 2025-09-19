<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 style="color: #2e7d32;">${video.title}</h2>
	<video width="100%" height="400" controls>
		<source src="${pageContext.request.contextPath}/${video.path}"
			type="video/mp4">
		Trình duyệt không hỗ trợ video.
	</video>
	<p>${video.description}</p>

</body>
</html>