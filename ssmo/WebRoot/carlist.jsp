<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML >
<html>
<head>

<title>ssmo demo</title>

</head>
<script src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("tr").filter(":odd").css("background-color", "skyblue");
		$("tr").filter(":even").css("background-color", "white");
	});
</script>

<body>
	<h1>SpringMVC- Spring -Mybatis-Oracle</h1>
	<img alt="MVC" width="100" height="100"
		src="${pageContext.request.contextPath }/resources/images/MVC.png">
	<form action="carController_find" method="post" name="f">
		<input tyle="text" name="name" placeholder="name"> <input
			type="date" name="beginDate" placeholder="开始时间"> <input
			type="date" name="endDate" placeholder="结束时间"> <input
			type="submit" value="搜索">
	</form>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Sale Date</td>
			<td>Price</td>
			<td>操作</td>
		</tr>

		<c:forEach var="car" items="${requestScope.cars }">
			<tr>
				<td>${car.id }</td>
				<td>${car.name }</td>
				<td><fmt:formatDate value="${car.saleDate }"
						pattern="yyyy-MM-dd" /></td>
				<td>${car.price}</td>

				<td><a href="carController_findById?id=${car.id }">Modify </a>
					&nbsp;&nbsp; <a href="carController_remove?id=${car.id }"
					onclick="return confirm('是否确认删除${car.name }?')">Remove</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="caredit.jsp">添加数据</a>
</body>
</html>
