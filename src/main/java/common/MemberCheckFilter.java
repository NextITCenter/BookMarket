package common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.MemberVO;

public class MemberCheckFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {
			chain.doFilter(req, res);// 원래 실행하려고 하던 서블릿의 메소드를 호출
		} else {
			// 원래 실행하려고 했던 요청 정보를 세션에 담아준다.
			String retUrl = req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : "") ;
			session.setAttribute("retUrl", retUrl);
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
