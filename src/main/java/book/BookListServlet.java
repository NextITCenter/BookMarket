package book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
					description,
					publisher,
					category,
					quantity,
					release_date,
					condition,
					image_filename
				from
					book
			""";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			List<BookVO> list = new ArrayList<BookVO>();
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String title = resultSet.getString("title");
				int price = resultSet.getInt("price");
				String author = resultSet.getString("author");
				String description = resultSet.getString("description");
				String publisher = resultSet.getString("publisher");
				String category = resultSet.getString("category");
				long quantity = resultSet.getLong("quantity");
				Date releaseDate = resultSet.getDate("release_date");
				String condition = resultSet.getString("condition");
				list.add(
					new BookVO(id, title, price, author, description, publisher,
							category, quantity, releaseDate.toLocalDate(), condition)
				);
			}
			req.setAttribute("books", list);
			req.getRequestDispatcher("/WEB-INF/views/book/books.jsp").forward(req, resp);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}








