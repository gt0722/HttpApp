<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML >
<html>
  <head>
   
    <title>ssmo demo</title>
	
  </head>
  
  <body>
  <h1>SpringMVC- Spring -Mybatis-Oracle</h1>
   <form action="carController_save" method="post" name="f">
   <input type="hidden" name="id" value="${car.id }"> 
   
   <input type="text" name="name" placeholder="name" value=${car.name }> <br>
   <c:if test="${empty car }">
   <input type="date" name="saleDate" placeholder="销售日期" > <br>
   </c:if>
   <c:if test="${!empty car }">
    <input type="date" name="saleDate" placeholder="销售日期" value='<spring:eval expression="car.saleDate"/>' > <br>
   </c:if>
   <input type="number" name="price" placeholder="销售价格" value="${car.price }"> <br>
   <input type="submit" value="save">
   </form>
   

  </body>
</html>
