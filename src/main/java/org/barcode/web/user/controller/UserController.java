package org.barcode.web.user.controller;

import jakarta.validation.Valid;
import org.barcode.core.user.service.UserService;
import org.barcode.web.user.dto.UserRequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// URI: /user 즉, 경로 지정하는 것.
// 즉, 클래스 단위의 URL + 메서드 단위의 URL이 합쳐져 전체 경로가 완성됨.
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // URI: /user/signup
    // 회원가입 홈페이지 리턴
    // @RequestParam Vs. @ModelAttribute/DTO Binding
    // 1~2개의 단일 값을 받을
    @GetMapping("/signup")
    public String showSignupForm(@ModelAttribute UserRequestRecord userRequestRecord) {
        return "signup";
    }

    // URI: /user/signup
    // submit 딸깍 했을 때
    // @Valid : 각 입력값에 대한 유효성 검사. 따라서 논리적 유효성 검사는 내가 별도로 수행해야 함
    // 유효성 검사 모두 통과하면 => 회원가입 완료 후 플래시 메시지 후 로그인 페이지 이동
    // 유효성 검사 통과 못하면, 다시 그 페이지 재 로딩.. 그때엔.. 폼 채워서 다시 그 페이지 포워딩?

    // 그럼 로직 검사는 뭐가 있지?
    @PostMapping("/signup")
    public String submitSignupForm(@ModelAttribute @Valid UserRequestRecord userRequestRecord, BindingResult bindingResult) {
        // BindingResult : @Valid, @ModelAttribute 등으로 객체를 바인딩하고 유효성 검사를 했을 때, 발생한 오류를 담는 객체.
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.signUp(userRequestRecord);
        return "redirect:/login";

    }

    @PostMapping("/uniqueEmail")
    public String checkUniqueEmail(@RequestParam String userEmail) {
        // 이메일 중복 체크 버튼용..
        return "";
    }
}
