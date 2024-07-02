package board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

@WebServlet("/boards/update")
public class BoardUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long no = Long.parseLong(req.getParameter("no"));
		ServletContext context = req.getServletContext();
		SqlSession session = (SqlSession) context.getAttribute("sqlSession");
		
		BoardService service = new BoardService(session);
		BoardVO board = service.selectBoard(no);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 정보 받기
		long no = Long.parseLong(req.getParameter("no"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// 작성자는 session에서 꺼내와도 되고 파리미터로 받아도 되는데 이번에는 파라미터로 받는 걸
		// 해봅시다
		String writer = req.getParameter("writer");

		ServletContext context = req.getServletContext();
		SqlSession sqlSession = (SqlSession) context.getAttribute("sqlSession");

		BoardService service = new BoardService(sqlSession);
		service.updateBoard(new BoardVO(no, title, content, writer));
		resp.sendRedirect(req.getContextPath() + "/boards");
	}
}





