package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class BoardService {
	private BoardMapper mapper = null;
	public BoardService(SqlSession session) {
		mapper = session.getMapper(BoardMapper.class);
	}
	
	public List<BoardVO> selectBoards() {
		return mapper.selectBoards();
	}
	
	public BoardVO selectBoard(long no) {
		return mapper.selectBoard(no);
	}
	
	public int insertBoard(BoardVO board) {
		return mapper.insertBoard(board);
	}
	
	public int updateBoard(BoardVO board) {
		return mapper.updateBoard(board);
	}
	
	public int deleteBoard(long no) {
		return mapper.deleteBoard(no);
	}
}










