<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <style>
        body {
            background-color: #e6f4ea;
            font-family: 'Segoe UI', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .signup-container {
            background-color: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 10px #ccc;
            width: 300px;
        }

        .signup-container h2 {
            text-align: center;
            color: #2c6e49;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-sizing: border-box;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #88c999;
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #70b28b;
        }

        .error-message {
            color: red;
            font-size: 12px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="signup-container">
    <h2>회원가입</h2>
    <form id="signupForm" method="post" action="/signup">
        <input type="text" id="userId" name="userId" placeholder="아이디">
        <div class="error-message" id="userIdError"></div>

        <input type="password" id="userPw" name="userPw" placeholder="비밀번호">
        <div class="error-message" id="userPwError"></div>

        <input type="text" id="userName" name="userName" placeholder="이름">
        <div class="error-message" id="userNameError"></div>

        <input type="text" id="userBirth" name="userBirth" placeholder="생년월일 (YYMMDD)">
        <div class="error-message" id="userBirthError"></div>

        <input type="text" id="userPhoneNumber" name="userPhoneNumber" placeholder="전화번호 (01012345678)">
        <div class="error-message" id="userPhoneNumberError"></div>

        <button type="submit">회원가입</button>
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const form = document.getElementById("signupForm");

        form.addEventListener("submit", (e) => {
            let isValid = true;

            const userId = document.getElementById("userId").value;
            const idRegex = /^[a-z0-9]{5,20}$/;
            if (!idRegex.test(userId)) {
                isValid = false;
                document.getElementById("userIdError").innerText = "아이디는 영문 소문자/숫자 5~20자여야 합니다.";
            } else {
                document.getElementById("userIdError").innerText = "";
            }

            const userPw = document.getElementById("userPw").value;
            const pwRegex = /^[A-Za-z0-9!@#$%^&*]{8,16}$/;
            if (!pwRegex.test(userPw)) {
                isValid = false;
                document.getElementById("userPwError").innerText = "비밀번호는 8~16자, 영문/숫자/특수문자만 허용됩니다.";
            } else {
                document.getElementById("userPwError").innerText = "";
            }

            const userName = document.getElementById("userName").value;
            if (userName.length === 0 || userName.length > 10) {
                isValid = false;
                document.getElementById("userNameError").innerText = "이름은 1~10자 사이여야 합니다.";
            } else {
                document.getElementById("userNameError").innerText = "";
            }

            const userBirth = document.getElementById("userBirth").value;
            const birthRegex = /^\d{6}$/;
            if (!birthRegex.test(userBirth)) {
                isValid = false;
                document.getElementById("userBirthError").innerText = "YYMMDD 형식의 6자리 숫자를 입력하세요.";
            } else {
                const mm = parseInt(userBirth.substring(2, 4));
                const dd = parseInt(userBirth.substring(4, 6));
                if (mm < 1 || mm > 12 || dd < 1 || dd > 31) {
                    isValid = false;
                    document.getElementById("userBirthError").innerText = "유효한 월(01~12), 일(01~31)을 입력하세요.";
                } else {
                    document.getElementById("userBirthError").innerText = "";
                }
            }

            const phone = document.getElementById("userPhoneNumber").value;
            const phoneRegex = /^010\d{8}$/;
            if (!phoneRegex.test(phone)) {
                isValid = false;
                document.getElementById("userPhoneNumberError").innerText = "전화번호는 010으로 시작하는 11자리 숫자여야 합니다.";
            } else {
                document.getElementById("userPhoneNumberError").innerText = "";
            }

            if (!isValid) e.preventDefault();
        });
    });
</script>
</body>
</html>
