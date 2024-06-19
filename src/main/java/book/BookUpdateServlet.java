package book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/update")
@MultipartConfig
public class BookUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateId = req.getParameter("id");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "web03", "web03");
			String sql = """
					select
						id,
						title,
						price,
						author,
						publisher,
						release_date,
						description,
						category,
						quantity,
						condition
					from
						book
					where
						id = ?
					""";
			statement = connection.prepareStatement(sql);
			statement.setString(1, updateId);
			resultSet = statement.executeQuery();
			BookVO book = null;
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String title = resultSet.getString("title");
				int price = resultSet.getInt("price");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				Date releaseDate = resultSet.getDate("release_date");
				String description = resultSet.getString("description");
				String category = resultSet.getString("category");
				long quantity = resultSet.getLong("quantity");
				String condition = resultSet.getString("condition");
				book = new BookVO(id, title, price, author, description, publisher, category, quantity, releaseDate.toLocalDate(), condition);
			}
			req.setAttribute("book", book);
			req.getRequestDispatcher("/WEB-INF/views/book/update.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		int price = Integer.parseInt(req.getParameter("price"));
		String author = req.getParameter("author");
		String description = req.getParameter("description");
		String publisher = req.getParameter("publisher");
		String category = req.getParameter("category");
		Long quantity = Long.parseLong(req.getParameter("quantity"));
		Date releaseDate = Date.valueOf(req.getParameter("releaseDate"));
		String condition = req.getParameter("condition");
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "web03", "web03");
			String sql = """
					update
						book
					set
						title = ?,
						price = ?,
						author = ?,
						description = ?,
						publisher = ?,
						category = ?,
						quantity = ?,
						release_date = ?,
						condition = ?
					where
						id = ?
					""";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, title);
			statement.setInt(2, price);
			statement.setString(3, author);
			statement.setString(4, description);
			statement.setString(5, publisher);
			statement.setString(6, category);
			statement.setLong(7, quantity);
			statement.setDate(8, releaseDate);
			statement.setString(9, condition);
			statement.setString(10, id);
			
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				resp.sendRedirect(req.getContextPath() + "/books");
			} else {
				req.getRequestDispatcher("/WEB-INF/views/book/update.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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





