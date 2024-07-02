package board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import login.MemberVO;

@WebServlet("/boards/insert")
public class BoardInsertServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/board/insert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// 작성자는 세션에서 가져온다.
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		String writer = member.getEmail();
		// TODO: 만약 로그인된 사용자가 없으면 filter에서 로그인 체크한 뒤 게시글 목록으로 보내준다.

		ServletContext context = req.getServletContext();
		SqlSession sqlSession = (SqlSession) context.getAttribute("sqlSession");

		BoardService service = new BoardService(sqlSession);
		service.insertBoard(new BoardVO(title, content, writer));
		resp.sendRedirect(req.getContextPath() + "/boards");
	}

}










