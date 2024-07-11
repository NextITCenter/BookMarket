package kr.or.nextit.bookmarket.board;

import kr.or.nextit.bookmarket.common.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
	int selectBoardsTotalCount(SearchVO search);

	List<BoardVO> selectBoards(SearchVO search);

	BoardVO selectBoard(long no);

	void updateHits(long no);

	int insertBoard(BoardVO board);

	int updateBoard(BoardVO board);

	int deleteBoard(long no);
}
