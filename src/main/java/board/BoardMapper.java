package board;

import java.util.List;

public interface BoardMapper {
	List<BoardVO> selectBoards();
	
	BoardVO selectBoard(long no);
	
	int insertBoard(BoardVO board);
	
	int updateBoard(BoardVO board);
	
	int deleteBoard(long no);
}
