package org.barcode.core.user.service;

import jakarta.validation.Valid;
import org.barcode.Exception.UserAlreadyExistsException;
import org.barcode.core.user.domain.User;
import org.barcode.core.user.repository.UserMapper;
import org.barcode.web.user.dto.UserRequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    // 필요한 것들.. 뭐가 있을 까..
    private final UserMapper userMapper; // Mapper(DAO) 가져와서 사용해야지.


    // 의존성 주입 => 롬복 @RequiredArgsConstructor 대체 가능.
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    // 롬복 안씀. 빌더애너테이션 못씀.
    public static User toUser(UserRequestRecord req){
        User user = new User(req.userEmail(), req.userPassword(), req.userLastName(), req.userFirstName(), req.userPhoneNumber(), req.userBirthDate());
        user.setAgreed(true);
        user.setUserStatus('A');
        user.setUserRole('N');
        return user;

    }

    public boolean isUniqueEmail(String email) {
        int count = userMapper.countByUserEmail(email);
        return count == 0;
    }


    // 유스케이스 정리 : 서비스가 실제로 해결해야 할 시나리오를 글로 써보기
    // 1. 유저가 회원 가입 폼에 맞춰 정보를 작성. (User 도메인 객체 or DTO 객체)
    // 2. 1차 유효성 검사는 앞단에서 JS로 수행.
    // 3. ID 중복 검사의 경우, 별도의 버튼을 만들어 서버와 소통하며 검사할 수 있도록 작성
    // 4. ID 중복 검사 통과, 앞단의 유효성 검사 통과 하면, 서버 단에서 2차 검사
    // 5. 2차 검사 통과하면, DB에 저장 => 회원 가입 성공, 알림 후 로그인 페이지로 이동.
    // 6. 통과 못하면, DB에 저장 안하고 오류 내용 리턴 후 회원가입 페이지로 포워딩. (트랜잭션 관리 해야함).

    public void signUp(@Valid UserRequestRecord userRequestRecord) {
        // 이메일이 기존에 없으면 0, 있으면 1.
        boolean isUnique = isUniqueEmail(userRequestRecord.userEmail()); // 레코드의 게터는 getXXX가 아니고 그냥 필드이름이다. 개신기.
        if (!isUnique) {
            throw new UserAlreadyExistsException("이미 가입된 이메일입니다.");
        }
        User user = toUser(userRequestRecord);
        userMapper.insertUser(user);
    }




}
