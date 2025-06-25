

import org.barcode.core.user.domain.User;
import org.barcode.core.user.repository.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 회원가입 컨트롤러 통합 테스트
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:/spring/servlet-context.xml",
        "classpath:/spring/root-context.xml"
})
@Transactional   // 각 테스트 후 자동 롤백 (성공 케이스는 @Rollback(false)로 덮어씀)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserMapper userMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /* ==========================================================
     * 1. 이메일 형식 오류 → 검증 실패
     * ========================================================= */
    @Test
    void signup_invalidEmail_shouldReturnFieldError() throws Exception {

        mockMvc.perform(post("/user/signup")
                        .param("userEmail", "bad-email")          // ✗ invalid
                        .param("userPassword", "Abcd1234!")       // ✓ valid
                        .param("userLastName", "장")
                        .param("userFirstName", "신원")
                        .param("userPhoneNumber", "01012345678")
                        .param("userBirthDate", "1980-01-01"))
                .andDo(print())                               // 요청·응답 로그 확인
                .andExpect(status().isOk())                   // 다시 폼으로 포워드
                .andExpect(model().attributeHasFieldErrors(
                        "userRequestRecord", "userEmail"))    // ✗ 이메일 필드 오류 존재
                .andExpect(view().name("signup"));
    }

    /* ==========================================================
     * 2. 정상 입력 → DB 저장 확인
     *    (롤백 끄기 위해 @Rollback(false) 사용)
     * ========================================================= */
    @Test
    @Rollback(false)   // ★ 실제 INSERT 결과를 커밋해 보기
    void signup_success_shouldPersistUser() throws Exception {

        /* -- 중복되지 않도록 랜덤 이메일 생성 -- */
        String Email = "test22445@example.com";

        mockMvc.perform(post("/user/signup")
                        .param("userEmail", Email)
                        .param("userPassword", "Abcd1234!")
                        .param("userLastName", "김")
                        .param("userFirstName", "테스트")
                        .param("userPhoneNumber", "01099998888")
                        .param("userBirthDate", "1990-12-31"))
                .andExpect(status().is3xxRedirection())        // success → redirect
                .andExpect(redirectedUrl("/login"));

        /* -- 실제 DB 에 저장되었는지 확인 -- */
        User saved = userMapper.findUserByEmail(Email);
        assert saved != null : "사용자가 DB에 저장되지 않았습니다";
    }
}
