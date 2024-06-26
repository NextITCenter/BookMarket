package book;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
		LocalDate releaseDate = req.getParameter("releaseDate") != null ?
				LocalDate.parse(req.getParameter("releaseDate")) : null;
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
		
		BookService service = BookService.getInstance();
		int executeUpdate = service.insertBook(new BookVO(id, title, price, author, description, publisher, category, quantity, releaseDate, condition));
		
		if (executeUpdate > 0) {
			resp.sendRedirect(req.getContextPath() + "/books");
		} else {
			req.getRequestDispatcher("/WEB-INF/views/book/list.jsp").forward(req, resp);
		}
	}
}









