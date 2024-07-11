package kr.or.nextit.bookmarket.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public String selectBooks(Model model) {
        List<BookVO> books = service.selectBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/books/view")
    public String selectBook(Model model, String id) {
        BookVO book = service.selectBook(id);
        model.addAttribute("book", book);
        return "book/view";
    }

    @GetMapping("/books/insert")
    public String insertBook() {
        return "book/new";
    }

    @PostMapping("/books/insert")
    public String insertBook(BookVO book, MultipartFile imageFile) throws IOException {
        String filename = book.getId() + ".jpg";
        // Paths, Files 경로와 파일에 관련된 유틸리티 클래스
        // 파일을 저장할 위치(경로)
        Path path = Paths.get("c:\\", "users", "user", "book", "images", filename);
        // 파일 저장
        imageFile.transferTo(path);

        // 이미지 파일명 저장
        book.setImageFilename(filename);

        service.insertBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/update")
    public String updateBook(String id, Model model) {
        BookVO book = service.selectBook(id);
        model.addAttribute("book", book);
        return "book/update";
    }

    @PostMapping("/books/update")
    public String updateBook(BookVO book, MultipartFile imageFile) throws IOException {
        String filename = book.getId() + ".jpg";
        // Paths, Files 경로와 파일에 관련된 유틸리티 클래스
        // 파일을 저장할 위치(경로)
        Path path = Paths.get("c:\\", "users", "user", "book", "images", filename);
        // 파일 저장
        imageFile.transferTo(path);

        // 이미지 파일명 저장
        book.setImageFilename(filename);

        service.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(String id) {
        service.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/books/image")
    public void image(Model model, String id, HttpServletResponse resp) throws IOException {
        String filename = id + ".jpg";
        Path path = Paths.get("c:\\", "users", "user", "book", "images", filename);
        // 이미지 파일로 응답을 해줘야한다.
        // 응답 헤더에 파일 이름, 컨텐트 타입, 캐시여부 등등 설정
        resp.setHeader("Content-Type", "application/octet-stream");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename + ";");
        // 파일을 스트림으로 전송한다.
        OutputStream outputStream = resp.getOutputStream();
        // Paths, Files를 활용하면 간단하게 파일을 관리할 수 있다.
        // java.io 패키지에 Path, File이 있었다면
        // java.nio 패키지에 Paths, Files가 존재한다.
        Files.copy(path, outputStream);
    }
}
