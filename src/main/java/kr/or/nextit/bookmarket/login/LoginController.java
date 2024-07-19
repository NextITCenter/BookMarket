package kr.or.nextit.bookmarket.login;

import kr.or.nextit.bookmarket.member.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final LoginService service;
    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginVO login, String rememberMe, HttpSession session, HttpServletResponse resp, Model model) {
        MemberVO member = service.findByEmailAndPassword(login);
        if (member != null) {
            // 세션에 저장
            session.setAttribute("member", member);
            if ("remember-me".equals(rememberMe)) {
                // 쿠키도 등록(이메일)
                Cookie cookie = new Cookie("rememberMe", login.getEmail());
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 100);
                resp.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("rememberMe", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
            // 세션에 retUrl이라는 값이 존재하면 그 주소로 요청을 보내고
            // 없으면 메인화면으로 보낸다.
            String retUrl = (String) session.getAttribute("retUrl");
            String url = retUrl != null ? retUrl : "/";
            // 세션에 남아 있는 retUrl 제거
            session.removeAttribute("retUrl");
            return "redirect:" + url;
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
