package book;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/books/update")
@MultipartConfig
public class BookUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateId = req.getParameter("id");
		BookService service = BookService.getInstance();
		BookVO book = service.selectBook(updateId);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/WEB-INF/views/book/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		int price = req.getParameter("price") != null ?
				Integer.parseInt(req.getParameter("price")) : 0;
		String author = req.getParameter("author");
		String description = req.getParameter("description");
		String publisher = req.getParameter("publisher");
		String category = req.getParameter("category");
		long quantity = Long.parseLong(req.getParameter("quantity"));
		LocalDate releaseDate = req.getParameter("releaseDate") != null ? LocalDate.parse(req.getParameter("releaseDate")) : null;
		String condition = req.getParameter("condition");
		
		// 스트림형식으로 업로드된 파일 Part로 가져오기
		Part part = req.getPart("imageFile");
		String filename = id + ".jpg";
		// 파일이 저장될 경로
		Path path = Paths.get("c:\\", "Users", "user", "book", "images", filename);
		// 파일 쓰기
		part.write(path.toString());
		
		BookService service = BookService.getInstance();
		int executeUpdate = service.updateBook(new BookVO(id, title, price, author, description, publisher, category, quantity, releaseDate, condition, filename));
		
		if (executeUpdate > 0) {
			resp.sendRedirect(req.getContextPath() + "/books");
		} else {
			req.getRequestDispatcher("/WEB-INF/views/book/update.jsp").forward(req, resp);
		}

	}
}





