package book;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/books/insert")
@MultipartConfig
public class BookInsertServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/book/new.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");
		String title = req.getParameter("title");
		int price = req.getParameter("price").isEmpty() ?
				0 : Integer.parseInt(req.getParameter("price"));
		String author = req.getParameter("author");
		String description = req.getParameter("description");
		String publisher = req.getParameter("publisher");
		String category = req.getParameter("category");
		long quantity = req.getParameter("quantity").isEmpty() ?
				0 : Integer.parseInt(req.getParameter("quantity"));
		String releaseDate = req.getParameter("releaseDate");
		String condition = req.getParameter("condition");
		// 첨부파일은 Part라는 객체(인터페이스)로 처리할 수 있다.
		Part part = req.getPart("imageFile");
		// 원본 파일 이름
//		part.getSubmittedFileName();
		String filename = id + ".jpg";
		// 파일을 저장할 위치(경로)
		// Paths, Files 경로와 파일에 관련된 유틸리티 클래스
		Path path = Paths.get("c:\\", "users", "user", "book", "images", filename);
		// 파일 저장
		part.write(path.toString());

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "web03", "web03");
			StringBuilder builder = new StringBuilder();
			builder.append("insert into book ");
			builder.append("	(id, title, price, author, description, publisher, category, quantity, release_date, condition, image_filename) ");
			builder.append("values ");
			builder.append("	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			String sql = builder.toString();

			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, title);
			statement.setInt(3, price);
			statement.setString(4, author);
			statement.setString(5, description);
			statement.setString(6, publisher);
			statement.setString(7, category);
			statement.setLong(8, quantity);
			statement.setDate(9, Date.valueOf(releaseDate));
			statement.setString(10, condition);
			statement.setString(11, filename);
			
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				resp.sendRedirect(req.getContextPath() + "/books");
			} else {
				req.getRequestDispatcher("/WEB-INF/views/book/list.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}









