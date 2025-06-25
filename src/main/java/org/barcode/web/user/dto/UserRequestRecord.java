package org.barcode.web.user.dto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/*
    Java Record란?
    Java 14에서 추가된 클래스 타입으로,
    불변 데이터 객체를 쉽게 만들 수 있으며,
    더 간결하고 효율적으로 데이터 객체를 생성할 수 있도록 설계됨.

    record는 필드를 기반으로, 게터 뿐만 아니라 (불변 객체이므로 세터는 없음)
    equals(), hashCode(), toString(), copyOf(), toBuilder() 메서드를 자동 생성함.

    생성자도 자동 생성되며, 필요에 따라 명시적인 생성자를 정의할 수 있음.

    또한 불변성이 보장되어 데이터의 안정성을 높일 수 있다.

 */

// 어떤 밸리데이션이 필요할까..
// userEmail : 필수, 이메일, 최대 n자
// userPassword : 필수, 8~16자, pattern(숫자, 영문 대/소문자, 특수문자!@#$%만 허용)
// userPasswordConfirm << 이건 근데 JS 단에서만 확인해도 될 것 같은데.. 굳이 DB에 저장할 것도 아니고.
// userLastName : 필수, 최대 n자, pattern 영어한글만 가능
// userFirstName : 필수, 최대 n자, pattern 영어한글만 가능
// userPhoneNumber : 필수, 최대최소11자, 숫자만 입력 가능
// userBirthDate : 필수(NotNull), 과거,
// NotBlack는 String 전용


public record UserRequestRecord(
        @NotBlank @Email @Size(max = 100) String userEmail,
        @NotBlank @Size(min = 8, max = 16) @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%])[a-zA-Z\\d!@#$%]{8,16}$") String userPassword,
        // message = message.properties 도 가능하고, 문자열 리터럴로 직접 할당도 가능
        // 이때, properties 쓰면 재사용성, 유지보수성 향상되므로 저게 좀 더 권장.
        @NotBlank @Size(max = 20) @Pattern(regexp = "^[가-힣a-zA-Z]+$") String userLastName,
        @NotBlank @Size(max = 20) @Pattern(regexp = "^[가-힣a-zA-Z]+$") String userFirstName,
        @NotBlank @Size(min = 11, max = 11) @Pattern(regexp = "^[0-9]+$") String userPhoneNumber,
        @NotNull @Past LocalDate userBirthDate) {}