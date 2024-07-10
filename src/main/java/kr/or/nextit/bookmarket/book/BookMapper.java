package kr.or.nextit.bookmarket.book;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
	List<BookVO> selectBooks();
	BookVO selectBook(String searchId);
	int insertBook(BookVO book);
	int updateBook(BookVO book);
	int deleteBook(String deleteId);
}
