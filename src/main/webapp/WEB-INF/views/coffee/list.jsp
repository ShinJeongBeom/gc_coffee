<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags and Bootstrap CSS -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Coffee List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <style>
        body {
            background: #ddd;
        }

        .card {
            margin: auto;
            max-width: 950px;
            width: 90%;
            box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            border-radius: 1rem;
            border: transparent;
        }

        .summary {
            background-color: #ddd;
            border-top-right-radius: 1rem;
            border-bottom-right-radius: 1rem;
            padding: 4vh;
            color: rgb(65, 65, 65)
        }

        @media (max-width: 767px) {
            .summary {
                border-top-right-radius: unset;
                border-bottom-left-radius: 1rem
            }
        }

        .row {
            margin: 0
        }

        .title b {
            font-size: 1.5rem
        }

        .col-2,
        .col {
            padding: 0 1vh
        }

        img {
            width: 3.5rem
        }

        hr {
            margin-top: 1.25rem
        }

        .products {
            width: 100%;
        }

        .products .price, .products .action {
            line-height: 38px;
        }

        .products .action {
            line-height: 38px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
</div>
<div class="card">
    <div class="row">
        <!-- 좌측 상품 목록 영역 -->
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5 class="flex-grow-0"><b>상품 목록</b></h5>
            <ul class="list-group products">
                <c:forEach var="coffee" items="${coffeeList}">
                    <li class="list-group-item d-flex mt-3">
                        <div class="col-2">
                            <img class="img-fluid" src="https://i.imgur.com/HKOFQYa.jpeg" alt="커피 이미지">
                        </div>
                        <div class="col">
                            <div class="row text-muted">커피콩</div>
                            <div class="row">${coffee.coffeeName}</div>
                        </div>
                        <div class="col text-center price">${coffee.coffeePrice}원</div>
                        <div class="col text-end action">
                            <form action="/order/add" method="post" style="display:inline;">
                                <input type="hidden" name="coffeeId" value="${coffee.coffeeId}">
                                <input type="hidden" name="quantity" value="1">
                                <!-- email은 임시값, 실제 환경에 맞춰 변경 -->
                                <input type="hidden" name="email" value="test@example.com">
                                <button class="btn btn-small btn-outline-dark" type="submit">추가</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- 우측 Summary 영역 -->
        <div class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b>Summary</b></h5>
            </div>
            <hr>
            <c:if test="${not empty cart}">
                <c:forEach var="item" items="${cart}">
                    <div class="row mb-2">
                        <div class="col-8">
                            <h6 class="p-0 d-inline-block">${item.coffeeName}
                                <span class="badge bg-dark">${item.quantity}개</span>
                            </h6>
                        </div>
                        <!-- 삭제 버튼: 누르면 quantity가 1 감소 -->
                        <div class="col-4 text-end">
                            <form action="/coffee/removeItem" method="post" style="display:inline;">
                                <input type="hidden" name="coffeeId" value="${item.coffeeId}">
                                <button class="btn btn-sm btn-outline-danger" type="submit">삭제</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <form action="/order/checkout" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="email" class="form-control mb-1" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control mb-1" id="address" name="address" required>
                </div>
                <div class="mb-3">
                    <label for="postcode" class="form-label">우편번호</label>
                    <input type="text" class="form-control" id="postcode" name="zipcode" required>
                </div>
                <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
                <div class="row pt-2 pb-2 border-top">
                    <h5 class="col">총금액</h5>
                    <h5 class="col text-end">${totalPrice}원</h5>
                </div>
                <button class="btn btn-dark col-12" type="submit">결제하기</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
