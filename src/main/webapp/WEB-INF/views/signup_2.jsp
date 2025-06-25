<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background-color: #e0f7fa;
    }
    .container {
      width: 500px;
      margin: 40px auto;
      background: white;
      padding: 30px;
      border-radius: 15px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2 {
      text-align: center;
      color: #007bb6;
    }
    label {
      font-weight: bold;
      display: block;
      margin-top: 15px;
    }
    input[type="text"], input[type="password"], input[type="date"], input[type="email"] {
      width: 100%;
      padding: 8px;
      margin-top: 5px;
      border: 1px solid #ccc;
      border-radius: 8px;
    }
    .error {
      color: red;
      font-size: 0.9em;
    }
    .btn {
      background-color: #4fc3f7;
      border: none;
      padding: 10px 20px;
      margin-top: 20px;
      color: white;
      cursor: pointer;
      border-radius: 8px;
    }
    .btn:hover {
      background-color: #039be5;
    }
    .inline {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .agreement {
      margin-top: 15px;
    }
    .footer {
      text-align: center;
      margin-top: 20px;
    }
  </style>
  <script>
    function validateForm() {
      let valid = true;
      let messages = [];

      const email = document.getElementById("userEmail").value;
      const password = document.getElementById("userPassword").value;
      const confirm = document.getElementById("passwordConfirm").value;
      const lastName = document.getElementById("userLastName").value;
      const firstName = document.getElementById("userFirstName").value;
      const phone = document.getElementById("userPhoneNumber").value;
      const birth = document.getElementById("userBirthDate").value;
      const agree = document.getElementById("agree").checked;

      if (!email.includes("@")) {
        messages.push("이메일 형식이 올바르지 않습니다.");
        valid = false;
      }
      if (password.length < 8 || !/[0-9]/.test(password) || !/[!@#$%]/.test(password)) {
        messages.push("비밀번호는 8자 이상이며 숫자, 특수문자를 포함해야 합니다.");
        valid = false;
      }
      if (password !== confirm) {
        messages.push("비밀번호와 확인 값이 일치하지 않습니다.");
        valid = false;
      }
      if (!agree) {
        messages.push("약관에 동의해야 가입할 수 있습니다.");
        valid = false;
      }

      if (!valid) {
        alert(messages.join("\\n"));
      }

      return valid;
    }

    function checkEmail() {
      const email = document.getElementById("userEmail").value;
      if (!email) {
        alert("이메일을 입력해주세요.");
        return;
      }
      const popup = window.open("/user/uniqueEmail?userEmail=" + encodeURIComponent(email),
              "emailCheck", "width=400,height=200");
    }
  </script>
</head>
<body>
<div class="container">
  <h2>회원가입</h2>
  <form:form method="post" modelAttribute="userRequestRecord" onsubmit="return validateForm();">
    <div class="inline">
      <div style="width: 75%;">
        <label for="userEmail">이메일</label>
        <form:input path="userEmail" id="userEmail"/>
        <form:errors path="userEmail" cssClass="error"/>
      </div>
      <div style="width: 20%; text-align: right;">
        <button type="button" class="btn" onclick="checkEmail()">중복확인</button>
      </div>
    </div>

    <label for="userPassword">비밀번호</label>
    <form:password path="userPassword" id="userPassword"/>
    <form:errors path="userPassword" cssClass="error"/>

    <label for="passwordConfirm">비밀번호 확인</label>
    <input type="password" id="passwordConfirm" name="passwordConfirm"/>

    <label for="userLastName">성</label>
    <form:input path="userLastName" id="userLastName"/>
    <form:errors path="userLastName" cssClass="error"/>

    <label for="userFirstName">이름</label>
    <form:input path="userFirstName" id="userFirstName"/>
    <form:errors path="userFirstName" cssClass="error"/>

    <label for="userPhoneNumber">전화번호</label>
    <form:input path="userPhoneNumber" id="userPhoneNumber"/>
    <form:errors path="userPhoneNumber" cssClass="error"/>

    <label for="userBirthDate">생년월일</label>
    <form:input path="userBirthDate" id="userBirthDate" type="date"/>
    <form:errors path="userBirthDate" cssClass="error"/>

    <div class="agreement">
      <input type="checkbox" id="agree" name="agree"/>
      <label for="agree">약관에 동의합니다.</label>
    </div>

    <button type="submit" class="btn">회원가입</button>
  </form:form>

  <div class="footer">
    이미 계정이 있으신가요? <a href="/login">로그인</a>
  </div>
</div>
</body>
</html>
