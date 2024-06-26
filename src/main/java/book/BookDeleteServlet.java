package book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/delete")
public class BookDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deleteId = req.getParameter("id");
		BookService service = BookService.getInstance();
		
		int executeUpdate = service.deleteBook(deleteId);
		if (executeUpdate > 0) {
			resp.sendRedirect(req.getContextPath() + "/books");
		} else {
			// 삭제가 실패했을 경우
			req.setAttribute("msg", "도서 삭제를 실패했습니다.");
			req.getRequestDispatcher("/WEB-INF/views/book/view.jsp").forward(req, resp);
		}
	}
}













