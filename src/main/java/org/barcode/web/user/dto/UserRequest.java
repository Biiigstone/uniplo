package org.barcode.web.user.dto;



import java.time.LocalDate;

public class UserRequest {


    private String userEmail;
    private String userPassword;
    private String userLastName;
    private String userFirstName;
    private String userPhoneNumber;
    // yyyy-MM-dd 로 입력 받아야 함.
    private LocalDate userBirthDate;


    // 도메인 객체와 마찬가지로 기본 생성자가 있어야 리플렉션 사용 가능.
    public UserRequest() {}

    public UserRequest(String userEmail, String userPassword, String userLastName, String userFirstName, String userPhoneNumber, LocalDate userBirthDate) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userPhoneNumber = userPhoneNumber;
        this.userBirthDate = userBirthDate;
    }




    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public LocalDate getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setUserBirthDate(LocalDate userBirthDate) {
        this.userBirthDate = userBirthDate;
    }
}
