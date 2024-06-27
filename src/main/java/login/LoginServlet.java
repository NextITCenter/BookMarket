package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 쿠키에 remember-me가 존재하면 가져와서 로그인페이지의 email에 추가한다.
//		Cookie[] cookies = req.getCookies();
//		for (Cookie cookie : cookies) {
//			if ("remember-me".equals(cookie.getName())) {
//				String email = cookie.getValue();
//				req.setAttribute("email, cookies);
//			}
//		}
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String rememberMe = req.getParameter("rememberMe");
		// 아이디 비밀번호로 등록된 사용자 조회
		LoginService service = LoginService.getInstance();
		MemberVO member = service.findByEmailAndPassword(new LoginVO(email, password));
		if (member != null) {
			// 세션에 저장
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			if ("remember-me".equals(rememberMe)) {
				// 쿠키도 등록(이메일)
				Cookie cookie = new Cookie("rememberMe", email);
				cookie.setPath(req.getContextPath() + "/");
				cookie.setMaxAge(60 * 60 * 24 * 100);
				resp.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("rememberMe", null);
				cookie.setPath(req.getContextPath() + "/");
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
			resp.sendRedirect(req.getContextPath() + "/");
		} else {
			req.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
	}
}