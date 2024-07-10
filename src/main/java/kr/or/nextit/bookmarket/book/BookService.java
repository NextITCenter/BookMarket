package kr.or.nextit.bookmarket.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	private final BookMapper mapper;
	public BookService(BookMapper mapper) {
		this.mapper = mapper;
	}
	public List<BookVO> selectBooks() {
		return mapper.selectBooks();
	}
	public BookVO selectBook(String searchId) {
		return mapper.selectBook(searchId);
	}
	public int insertBook(BookVO book) {
		return mapper.insertBook(book);
	}
	public int updateBook(BookVO book) {
		return mapper.updateBook(book);
	}
	public int deleteBook(String deleteId) {
		return mapper.deleteBook(deleteId);
	}
}
