<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/style.css"></link>
<title>Products page</title>
</head>
<body>
	<table>
		<c:forEach items="${categoryList }" var="category">
			<tr>
				<td>${category.name }</td>
			</tr>
			<c:forEach items="${category.subcategoryList }" var="subcategory">
				<tr>
					<td></td>
					<td>${subcategory.name }</td>
				</tr>
				<c:forEach items="${subcategory.productList }" var="product">
					<tr>
						<td></td>
						<td></td>
						<td>${product.producer }</td>
						<td>${product.model }</td>
						<td>${product.color }</td>
						<td><fmt:formatDate value="${product.dateOfIssue }"
								pattern="dd-MM-yyyy" /></td>
						<td><c:choose>
								<c:when test="${product.notInStock }">
								not in stock
							</c:when>
								<c:otherwise>
								${product.price }
							</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</table>
	<form action="../ParserServlet">
		<input type="hidden" name="command" value="main_page"> 
		<input type="submit" value="Back">
	</form>
</body>
</html>