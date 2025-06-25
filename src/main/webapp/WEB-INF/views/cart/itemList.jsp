<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>장바구니</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cart.css" />
</head>
<body>
<div class="container">
    <header class="header">
        <div class="logo">MyShop</div>
        <nav>
            <a href="${pageContext.request.contextPath}/home">홈</a>
            <a href="${pageContext.request.contextPath}/product/itemList?userId=${param.userId}">상품 목록</a>
        </nav>
    </header>
    <main class="card">
        <h2>🛒 장바구니</h2>
        <c:if test="${not empty message}">
            <div class="msg success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="msg error">${error}</div>
        </c:if>
        <table class="cart-table">
            <thead>
            <tr>
                <th>상품 ID</th>
                <th>색상 코드</th>
                <th>사이즈 코드</th>
                <th>등록일</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><c:out value="${item.item_id}" /></td>
                    <td><c:out value="${item.item_color_code}" /></td>
                    <td><c:out value="${item.item_size_code}" /></td>
                    <td>
                        <fmt:formatDate value="${item.cart_reg_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart/itemRemove" method="post" class="inline-form">
                            <input type="hidden" name="userId" value="${param.userId}" />
                            <input type="hidden" name="item_id" value="${item.item_id}" />
                            <input type="hidden" name="item_color_code" value="${item.item_color_code}" />
                            <input type="hidden" name="item_size_code" value="${item.item_size_code}" />
                            <button type="submit" class="btn btn-small">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
<%--            <c:if test="${items.empty}">--%>
<%--                <tr>--%>
<%--                    <td colspan="5" class="no-data">장바구니가 비어 있습니다.</td>--%>
<%--                </tr>--%>
<%--            </c:if>--%>
            </tbody>
        </table>
        <div class="actions">
            <form action="${pageContext.request.contextPath}/cart/clear" method="post">
                <input type="hidden" name="userId" value="${param.userId}" />
                <button type="submit" class="btn">장바구니 비우기</button>
            </form>
            <a href="${pageContext.request.contextPath}/product/itemList?userId=${param.userId}" class="btn">상품 목록으로 돌아가기</a>
        </div>
    </main>
    <footer class="footer">
        <div>&copy; 2025 MyShop</div>
    </footer>
</div>
</body>
</html>
