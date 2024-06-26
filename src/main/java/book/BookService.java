package book;

import java.util.List;

import common.MyBatisSession;

public class BookService {
	private BookMapper mapper;
	private static BookService instance = new BookService();
	private BookService() {
		mapper = MyBatisSession.session.getMapper(BookMapper.class);
	}
	public static BookService getInstance() {
		return instance;
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
