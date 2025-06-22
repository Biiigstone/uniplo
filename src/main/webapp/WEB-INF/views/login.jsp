<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background: white;
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-sizing: border-box;
        }

        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #a2d5a2; /* 연한 초록 */
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: bold;
        }

        .login-container .extra-links {
            margin-top: 10px;
            font-size: 12px;
            text-align: center;
        }

        .login-container .extra-links a {
            margin: 0 5px;
            color: #555;
            text-decoration: none;
        }

        .login-container .extra-links a:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            font-size: 13px;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>로그인</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post" action="/login">
        <input type="text" name="userId" placeholder="아이디" required />
        <input type="password" name="password" placeholder="비밀번호" required />
        <button type="submit">로그인</button>
    </form>

    <div class="extra-links">
        <a href="/signup">회원가입</a> |
        <a href="#">아이디 찾기</a> |
        <a href="#">비밀번호 찾기</a>
    </div>
</div>

</body>
</html>
