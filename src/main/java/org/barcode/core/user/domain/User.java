package org.barcode.core.user.domain;


import java.time.LocalDate;

public class User {

    private long userId;
    private String userEmail;
    private String userPassword;
    private String userLastName;
    private String userFirstName;
    private String userPhoneNumber;
    private LocalDate userBirthDate;
    // 마케팅 정보 수신 동의 여부
    private Boolean isAgreed;
    // 유저 상태
    private char userStatus;
    private char userRole;

    // 프레임 워크가 객체 생성 시에 리플렉션을 사용하기 떄문이 기본 생성자는 필수이다.
    public User() {
    }

    // UserId는 keyProperty로 GeneratedKeys를 사용하므로, null로 비워둔다.
    public User(String userEmail, String userPassword, String userLastName, String userFirstName, String userPhoneNumber, LocalDate userBirthDate) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userPhoneNumber = userPhoneNumber;
        this.userBirthDate = userBirthDate;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userBirthDate=" + userBirthDate +
                ", isAgreed=" + isAgreed +
                ", userStatus=" + userStatus +
                ", userRole='" + userRole + '\'' +
                '}';
    }

    public long getUserId() {
        return userId;
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

    public Boolean getAgreed() {
        return isAgreed;
    }

    public char getUserStatus() {
        return userStatus;
    }

    public char getUserRole() {
        return userRole;
    }

    // MyBatis가 UserId 세트하기 위해선 세터가 필요하긴 하지만,
    // 직접 세트하면 안 되므로 접근제어자로 제한해야 한다.
    void setUserId(long userId) {
        this.userId = userId;
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

    public void setAgreed(Boolean agreed) {
        isAgreed = agreed;
    }

    public void setUserStatus(char userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserRole(char userRole) {
        this.userRole = userRole;
    }
}
