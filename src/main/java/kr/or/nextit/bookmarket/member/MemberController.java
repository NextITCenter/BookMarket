package kr.or.nextit.bookmarket.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService service;

    @GetMapping("/password")
    public String changePasswordView() {
        return "member/password";
    }
    @PostMapping("/password")
    public String changePassword(@ModelAttribute MemberRequest member) {
        service.updatePassword(member);
        return "redirect:/";
    }
}
