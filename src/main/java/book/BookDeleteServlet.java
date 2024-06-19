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
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "web03", "web03");
			String sql = "delete from book where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, deleteId);
			
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				resp.sendRedirect(req.getContextPath() + "/books");
			} else {
				// 삭제가 실패했을 경우
				req.setAttribute("msg", "도서 삭제를 실패했습니다.");
				req.getRequestDispatcher("/WEB-INF/views/book/view.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}













