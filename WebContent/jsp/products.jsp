<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products page</title>
</head>
<body>
<table>
  <tr>
    <th>Category</th>
    <th>Subcategory</th>
    <th>Name</th>
    <th>Model</th>
    <th>Color</th>
    <th>Date of issue</th>
    <th>Price</th>
    <th>Not in stock</th>
  </tr>
  <c:forEach items="${ categoryList }" var="category">
  <tr>
    <td>${category.name }</td>
    <td>${category.subcategory.name }</td>
    <td>${category.subcategory.product.name }</td>
    <td>${category.subcategory.product.model }</td>
    <td>${category.subcategory.product.color }</td>
    <td>${category.subcategory.product.dateOfIssue }</td>
    <td>${category.subcategory.product.price }</td>
    <td>${category.subcategory.product.notInStock }</td>
  </tr>
  </c:forEach>
</table>
</body>
</html>