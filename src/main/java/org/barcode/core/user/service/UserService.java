package org.barcode.core.user.service;

import org.barcode.core.user.repository.UserMapper;
import org.barcode.web.user.dto.UserRequest;
import org.barcode.web.user.dto.UserRequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 필요한 것들.. 뭐가 있을 까..
    private final UserMapper userMapper; // Mapper(DAO) 가져와서 사용해야지.
    private final UserRequest userRequest; // DTO도 가져올거고..
    private final UserRequestRecord userRequestRecord;

    // 의존성 주입 => 롬복 @RequiredArgsConstructor 대체 가능.
    @Autowired
    public UserService(UserMapper userMapper, UserRequest userRequest, UserRequestRecord userRequestRecord) {
        this.userMapper = userMapper;
        this.userRequest = userRequest;
        this.userRequestRecord = userRequestRecord;
    }



    // 유스케이스 정리 : 서비스가 실제로 해결해야 할 시나리오를 글로 써보기
    // 1. 유저가 회원 가입 폼에 맞춰 정보를 작성. (User 도메인 객체 or DTO 객체)
    // 2. 1차 유효성 검사는 앞단에서 JS로 수행.
    // 3. ID 중복 검사의 경우, 별도의 버튼을 만들어 서버와 소통하며 검사할 수 있도록 작성
    // 4. ID 중복 검사 통과, 앞단의 유효성 검사 통과 하면, 서버 단에서 2차 검사
    // 5. 2차 검사 통과하면, DB에 저장 => 회원 가입 성공, 알림 후 로그인 페이지로 이동.
    // 6. 통과 못하면, DB에 저장 안하고 오류 내용 리턴 후 회원가입 페이지로 포워딩. (트랜잭션 관리 해야함).








//    @Autowired
//    public RegisterService(UserDao userDao){
//        this.userDao = userDao;
//    }
//
//    // 회원가입
//    // 1. 사용자가 제출한 데이터에 대한 유효성 검사. 프론트에서 했더라도 수행해야한다.
//    // 2. 유효성 검사에 문제가 없다면, 제출한 ID가 기존 사용자와 중복되는지 확인한다.
//    // 3. 모든 검사를 통과하면, 사용자의 객체를 생성 후 저장한다.
//    // 리디렉션 포워딩 등등은 컨트롤러 단에서 처리.
//    // 예외 처리 관련해서도 해봐야하는데.. 이건 좀 나중에하자.
//    // 일단 여기선 IllegalArg로 일괄 처리.
//
//
//    // 이렇게 수정하면.. 책임의 분리가 좀 더 명확해져서 유지 보수성이 높아진다.
//    public boolean signup(SignupRequest req) {
//
//        // id가 유효하지 않으면
//        if (!Util.isValidId(req.getUserId())) {
//            throw new IllegalArgumentException("아이디 작성 규칙을 확인해주세요");
//        }
//
//        // 비밀번호가 유효하지 않으면
//        if (!Util.isValidPassword(req.getUserPw())) {
//            throw new IllegalArgumentException("비밀번호 작성 규칙을 확인해주세요");
//        }
//
//        // ID가 기존 사용자와 중복된다면
//        if (userDao.existsById(req.getUserId())) {
//            throw new IllegalArgumentException("아이디 중복");
//        }
//
//        // 근데 이 서비스 단에서 객체 생성까지 담당하는 것이 맞을까?
//        // DTO의 필요성 ..
//        // 서비스단에선 직접 생성하기보단, DTO에 객체 조립의 책임을 넘김
//        ShopUser newUser = req.toShopUser();
//        userDao.save(newUser);
//        return true;
//    }
//
//    // 아이디 중복 체크 메서드
//    public boolean isExistId(String userId) {
//        return userDao.existsById(userId);
//    }



}
