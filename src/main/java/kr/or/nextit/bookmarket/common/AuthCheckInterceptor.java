package kr.or.nextit.bookmarket.common;

import kr.or.nextit.bookmarket.member.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
//@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("==============AuthCheckInterceptor preHandle invoke!!================");
        HttpSession session = request.getSession();
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {
            return true;
        }
        // 원래 실행하려고 했던 요청 정보를 세션에 담아준다.
        String retUrl = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "") ;
        session.setAttribute("retUrl", retUrl);
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("**************AuthCheckInterceptor postHandle invoke!!********************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("##############AuthCheckInterceptor afterCompletion invoke!!#################");
    }
}
