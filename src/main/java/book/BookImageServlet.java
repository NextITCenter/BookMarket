package book;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/image")
public class BookImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String filename = id + ".jpg";
		Path path = Paths.get("c:\\", "users", "user", "book", "images", filename);
		// 이미지 파일로 응답을 해줘야한다.
		// 응답 헤더에 파일 이름, 컨텐트 타입, 캐시여부 등등 설정
		resp.setHeader("Content-Type", "application/octet-stream");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Content-Disposition", "attachement;filename=" + filename + ";");
		
		// 파일을 스트림으로 전송한다.
		OutputStream outputStream = resp.getOutputStream();
		// Paths, Files를 활용하면 간단하게 파일을 관리할 수 있다.
		// java.io 패키지에 Path, File이 있었다면
		// java.nio 패키지에 Paths, Files가 존재한다.
		
		Files.copy(path, outputStream);
		
//		File file = path.toFile();
//		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//		byte[] buffer = new byte[1024];
//		int read = 0;
//		while ((read = inputStream.read(buffer)) != -1) {
//			outputStream.write(buffer, 0, read);
//		}
	}

}







