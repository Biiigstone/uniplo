package org.barcode.web.user.controller;

import jakarta.validation.Valid;
import org.barcode.core.user.service.UserService;
import org.barcode.web.user.dto.UserRequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// 컨트롤러 나누기

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
    public String showSignupForm(@ModelAttribute("userRequestRecord") UserRequestRecord userRequestRecord) {
//        model.addAttribute("userRequestRecord", new UserRequestRecord(null, null, null, null, null, null));
        // 디버깅용
//        System.out.println(model.asMap().keySet());
        return "signup";
    }

    // URI: /user/signup
    // submit 딸깍 했을 때
    // @Valid : 각 입력값에 대한 유효성 검사. 따라서 논리적 유효성 검사는 내가 별도로 수행해야 함
    // 유효성 검사 모두 통과하면 => 회원가입 완료 후 플래시 메시지 후 로그인 페이지 이동
    // 유효성 검사 통과 못하면, 다시 그 페이지 재 로딩.. 그때엔.. 폼 채워서 다시 그 페이지 포워딩?

    // 그럼 로직 검사는 뭐가 있지?
    @PostMapping("/signup")
    public String submitSignupForm(@Valid @ModelAttribute("userRequestRecord") UserRequestRecord userRequestRecord, BindingResult bindingResult) {
        // BindingResult : @Valid, @ModelAttribute 등으로 객체를 바인딩하고 유효성 검사를 했을 때, 발생한 오류를 담는 객체.
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.signUp(userRequestRecord);
        return "redirect:/login";

    }

    @ResponseBody
    @GetMapping("/uniqueEmail")
    public String checkUniqueEmail(@RequestParam String userEmail) {
        boolean isUnique = userService.isUniqueEmail(userEmail);
        if (isUnique) {
            return "<script>alert('사용 가능한 이메일입니다.'); window.close();</script>";
        } else {
            return "<script>alert('이미 사용 중인 이메일입니다.'); window.close();</script>";
        }
    }
}
