<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <style>
    body {
      font-family: 'Pretendard', Arial, sans-serif;
      background: linear-gradient(135deg, #e0f0ff 0%, #b3dbff 100%);
      margin: 0; padding: 0;
    }
    .container {
      max-width: 500px;
      margin: 48px auto;
      background: #ffffffcc;
      border-radius: 28px;
      box-shadow: 0 8px 28px 0 rgba(120,180,255,0.20);
      padding: 2.7rem 2.4rem 2.2rem 2.4rem;
      border: 2.5px solid #a6c8ff44;
    }
    h2 {
      text-align: center;
      margin-bottom: 2.3rem;
      color: #288ae3;
      letter-spacing: 1px;
      font-weight: 700;
    }
    form label {
      font-weight: 600;
      display: block;
      margin-top: 1.28rem;
      color: #2472c8;
    }
    form input {
      width: 100%;
      padding: 0.7rem 1rem;
      margin-top: 0.5rem;
      border-radius: 14px;
      border: 1.8px solid #b4daff;
      background: #f8fbff;
      font-size: 1rem;
      transition: border 0.2s;
    }
    form input:focus {
      outline: none;
      border: 2.3px solid #4ab4ff;
      background: #e9f5ff;
    }
    .error {
      color: #db3367;
      margin-top: 0.16rem;
      font-size: 0.95em;
    }
    .spring-error {
      color: #c01648;
      margin: 0.14rem 0 0.2rem 0.1rem;
      font-size: 0.98em;
    }
    .btn {
      background: linear-gradient(90deg, #47bbff 0%, #2186d2 100%);
      color: #fff;
      border: none;
      border-radius: 15px;
      padding: 0.9rem;
      width: 100%;
      font-size: 1.11rem;
      margin-top: 2.1rem;
      font-weight: 600;
      letter-spacing: 0.5px;
      box-shadow: 0 2px 8px 0 #72cfff33;
      cursor: pointer;
      transition: background 0.15s;
    }
    .btn:disabled {
      background: #b0daff;
      cursor: not-allowed;
    }
    .flex-row {
      display: flex; gap: 0.7rem;
    }
    .flex-row > div { flex: 1; }
    .check-btn {
      margin-left: 0.4rem;
      padding: 0.54rem 1.18rem;
      background: linear-gradient(90deg, #6cd0ff 0%, #1e90ec 100%);
      color: #fff;
      border: none;
      border-radius: 11px;
      font-size: 0.97rem;
      font-weight: 500;
      cursor: pointer;
      transition: background 0.17s;
      box-shadow: 0 1px 6px 0 #68c5ff35;
    }
    .check-btn:active {
      background: #2f9ae7;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>회원가입</h2>
  <form id="signupForm" method="post" autocomplete="off">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label for="userEmail">이메일</label>
    <div class="flex-row">
      <div>
        <input type="email" id="userEmail" name="userEmail" maxlength="100" required placeholder="example@email.com"
               value="${userRequestRecord.userEmail}">
      </div>
      <button type="button" id="checkEmailBtn" class="check-btn">중복확인</button>
    </div>
    <c:if test="${not empty errors['userEmail']}">
      <div class="spring-error">${errors['userEmail'].defaultMessage}</div>
    </c:if>
    <div class="error" id="userEmailError"></div>
    <div class="error" id="userEmailUniqueMsg" style="margin-top:0.1rem;"></div>

    <label for="userPassword">비밀번호</label>
    <input type="password" id="userPassword" name="userPassword" maxlength="16" minlength="8" required placeholder="8~16자, 영문/숫자/특수문자(!@#$%) 포함"
           value="${userRequestRecord.userPassword}">
    <c:if test="${not empty errors['userPassword']}">
      <div class="spring-error">${errors['userPassword'].defaultMessage}</div>
    </c:if>
    <div class="error" id="userPasswordError"></div>

    <label for="userPasswordConfirm">비밀번호 확인</label>
    <input type="password" id="userPasswordConfirm" name="userPasswordConfirm" maxlength="16" minlength="8" required placeholder="비밀번호 재입력">
    <div class="error" id="userPasswordConfirmError"></div>

    <label for="userLastName">성</label>
    <input type="text" id="userLastName" name="userLastName" maxlength="20" required placeholder="홍" value="${userRequestRecord.userLastName}">
    <c:if test="${not empty errors['userLastName']}">
      <div class="spring-error">${errors['userLastName'].defaultMessage}</div>
    </c:if>
    <div class="error" id="userLastNameError"></div>

    <label for="userFirstName">이름</label>
    <input type="text" id="userFirstName" name="userFirstName" maxlength="20" required placeholder="길동" value="${userRequestRecord.userFirstName}">
    <c:if test="${not empty errors['userFirstName']}">
      <div class="spring-error">${errors['userFirstName'].defaultMessage}</div>
    </c:if>
    <div class="error" id="userFirstNameError"></div>

    <label for="userPhoneNumber">휴대폰 번호</label>
    <input type="text" id="userPhoneNumber" name="userPhoneNumber" maxlength="11" minlength="11" required placeholder="숫자 11자리(-없이)" value="${userRequestRecord.userPhoneNumber}">
    <c:if test="${not empty errors['userPhoneNumber']}">
      <div class="spring-error">${errors['userPhoneNumber'].defaultMessage}</div>
    </c:if>
    <div class="error" id="userPhoneNumberError"></div>

    <label for="userBirthDate">생년월일</label>
    <input type="date" id="userBirthDate" name="userBirthDate" required value="${userRequestRecord.userBirthDate}">
    <c:if test="${not empty errors['userBirthDate']}">
      <div class="spring-error">${errors['userBirthDate'].defaultMessage}</div>
    </c:if>
    <div class="error" id="userBirthDateError"></div>

    <div style="margin-top: 1.7rem; margin-bottom: 0.5rem; display: flex; align-items: center;">
      <input type="checkbox" id="agreeTerms" style="width:20px; height:20px; border-radius:6px; accent-color:#38b6ff; margin-right: 0.55rem;">
      <label for="agreeTerms" style="margin: 0; font-weight:500; color:#288ae3; font-size:1rem; cursor:pointer;">약관 및 개인정보 처리방침에 동의합니다.</label>
    </div>
    <div class="error" id="agreeTermsError" style="margin-bottom:1.2rem;"></div>

    <button type="submit" class="btn">가입하기</button>
    <div style="text-align:right; margin-top:1.2rem;">
      <a href="/login" style="color:#2186d2; text-decoration:underline; font-size:1.02rem; font-weight:500;">이미 계정이 있으신가요? 로그인하기 &rarr;</a>
    </div>
  </form>
</div>

<script>
  // 정규식 패턴 미리 선언
  const emailPattern = /^[\w\-.]+@[\w\-.]+\.[a-zA-Z]{2,}$/;
  const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%])[a-zA-Z\d!@#$%]{8,16}$/;
  const namePattern = /^[가-힣a-zA-Z]+$/;
  const phonePattern = /^\d{11}$/;

  let lastCheckedEmail = null;
  let lastEmailUnique = false;

  function validateForm() {
    let valid = true;
    ["userEmail","userPassword","userPasswordConfirm","userLastName","userFirstName","userPhoneNumber","userBirthDate","agreeTerms"]
            .forEach(id => {
              let err = document.getElementById(id+"Error");
              if (err) err.innerText = "";
            });
    document.getElementById("userEmailUniqueMsg").innerText = "";

    // 이메일
    const email = document.getElementById("userEmail").value.trim();
    if (!email) {
      document.getElementById("userEmailError").innerText = "이메일을 입력하세요.";
      valid = false;
    } else if (!emailPattern.test(email)) {
      document.getElementById("userEmailError").innerText = "이메일 형식이 올바르지 않습니다.";
      valid = false;
    } else if (!lastEmailUnique || email !== lastCheckedEmail) {
      document.getElementById("userEmailUniqueMsg").innerText = "이메일 중복 확인을 해주세요.";
      valid = false;
    }
    // 비밀번호
    const pw = document.getElementById("userPassword").value;
    if (!pw) {
      document.getElementById("userPasswordError").innerText = "비밀번호를 입력하세요.";
      valid = false;
    } else if (!passwordPattern.test(pw)) {
      document.getElementById("userPasswordError").innerText = "8~16자 영문/숫자/특수문자(!@#$%) 포함";
      valid = false;
    }
    // 비밀번호 확인
    const pwc = document.getElementById("userPasswordConfirm").value;
    if (!pwc) {
      document.getElementById("userPasswordConfirmError").innerText = "비밀번호 확인란을 입력하세요.";
      valid = false;
    } else if (pw !== pwc) {
      document.getElementById("userPasswordConfirmError").innerText = "비밀번호가 일치하지 않습니다.";
      valid = false;
    }
    // 성
    const lastName = document.getElementById("userLastName").value.trim();
    if (!lastName) {
      document.getElementById("userLastNameError").innerText = "성을 입력하세요.";
      valid = false;
    } else if (!namePattern.test(lastName)) {
      document.getElementById("userLastNameError").innerText = "한글 또는 영문만 입력 가능합니다.";
      valid = false;
    }
    // 이름
    const firstName = document.getElementById("userFirstName").value.trim();
    if (!firstName) {
      document.getElementById("userFirstNameError").innerText = "이름을 입력하세요.";
      valid = false;
    } else if (!namePattern.test(firstName)) {
      document.getElementById("userFirstNameError").innerText = "한글 또는 영문만 입력 가능합니다.";
      valid = false;
    }
    // 휴대폰 번호
    const phone = document.getElementById("userPhoneNumber").value.trim();
    if (!phone) {
      document.getElementById("userPhoneNumberError").innerText = "휴대폰 번호를 입력하세요.";
      valid = false;
    } else if (!phonePattern.test(phone)) {
      document.getElementById("userPhoneNumberError").innerText = "숫자 11자리(-없이) 입력";
      valid = false;
    }
    // 생년월일
    const birthDate = document.getElementById("userBirthDate").value;
    if (!birthDate) {
      document.getElementById("userBirthDateError").innerText = "생년월일을 입력하세요.";
      valid = false;
    } else {
      const today = new Date();
      const inputDate = new Date(birthDate);
      if (inputDate >= today) {
        document.getElementById("userBirthDateError").innerText = "과거 날짜만 입력 가능합니다.";
        valid = false;
      }
    }
    const agree = document.getElementById("agreeTerms").checked;
    if (!agree) {
      document.getElementById("agreeTermsError").innerText = "회원가입을 위해 약관 동의가 필요합니다.";
      valid = false;
    }
    return valid;
  }

  const form = document.getElementById("signupForm");
  form.addEventListener("submit", function(e) {
    if (!validateForm()) {
      e.preventDefault();
    }
  });

  // 이메일 중복확인 버튼
  const checkBtn = document.getElementById("checkEmailBtn");
  checkBtn.addEventListener("click", function() {
    const emailInput = document.getElementById("userEmail");
    const email = emailInput.value.trim();
    document.getElementById("userEmailUniqueMsg").innerText = "";
    if (!emailPattern.test(email)) {
      document.getElementById("userEmailError").innerText = "올바른 이메일을 입력하세요.";
      lastCheckedEmail = null;
      lastEmailUnique = false;
      return;
    }
    // AJAX 요청
    fetch(`/user/uniqueEmail?userEmail=" + encodeURIComponent(email), {method:'GET'})
      .then(resp => resp.text())
      .then(html => {
        // 서버에서 script 응답. 파싱 후 alert 내용 추출
        let msg;
        if (html.includes('사용 가능한 이메일')) {
          msg = '사용 가능한 이메일입니다.';
          lastCheckedEmail = email;
          lastEmailUnique = true;
          document.getElementById("userEmailUniqueMsg").style.color = '#22a141';
        } else {
          msg = '이미 사용 중인 이메일입니다.';
          lastCheckedEmail = email;
          lastEmailUnique = false;
          document.getElementById("userEmailUniqueMsg").style.color = '#c01648';
        }
        document.getElementById("userEmailUniqueMsg").innerText = msg;
      })
      .catch(err => {
        document.getElementById("userEmailUniqueMsg").innerText = '중복 확인 중 오류 발생';
        lastCheckedEmail = null;
        lastEmailUnique = false;
      });
});

document.getElementById("userEmail").addEventListener("input", function() {
    // 이메일 입력값이 바뀌면 중복체크 무효화
    lastCheckedEmail = null;
    lastEmailUnique = fals