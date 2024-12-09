<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container-fluid mt-4">
<h1>전체 주문 목록</h1>
<table class="table table-bordered table-striped table-hover mt-3">
    <thead class="table-dark">
    <tr>
        <th>주문 ID</th>
        <th>이메일</th>
        <th>주소</th>
        <th>우편번호</th>
        <th>주문 날짜</th>
        <th>상태(출고)</th>
        <th>커피 이름</th>
        <th>커피 가격</th>
        <th>수량</th>
        <th>총 금액</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${allOrders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.email}</td>
            <td>${order.address}</td>
            <td>${order.zipCode}</td>
            <td><c:out value="${order.orderDateTime}"/></td>
            <td><c:choose>
                <c:when test="${order.status}">출고완료</c:when>
                <c:otherwise>출고대기</c:otherwise>
            </c:choose></td>
            <td>${order.coffeeName}</td>
            <td>${order.coffeePrice}원</td>
            <td>${order.quantity}</td>
            <td>${order.totalPrice}원</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
