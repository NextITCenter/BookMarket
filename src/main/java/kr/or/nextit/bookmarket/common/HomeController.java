package kr.or.nextit.bookmarket.common;

import kr.or.nextit.bookmarket.login.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/index", "/home"}, method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/sessionCheck")
    public String sessionCheck(HttpSession session, @RequestParam("retUrl") String retUrl) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member == null) {
            session.setAttribute("retUrl", retUrl);
            return "/login";
        }
        return "exist";
    }
}
