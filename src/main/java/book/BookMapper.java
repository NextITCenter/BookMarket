package book;

import java.util.List;

public interface BookMapper {
	List<BookVO> selectBooks();
	BookVO selectBook(String searchId);
	int insertBook(BookVO book);
	int updateBook(BookVO book);
	int deleteBook(String deleteId);
}
