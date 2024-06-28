package board;

import java.util.List;

import common.MyBatisSession;

public class BoardService {
	private BoardMapper mapper = null;
	private static BoardService instance = new BoardService();
	private BoardService() {
		mapper = MyBatisSession.session.getMapper(BoardMapper.class);
	}
	public static BoardService getInstance() {
		return instance;
	}
	
	public List<BoardVO> selectBoards() {
		return mapper.selectBoards();
	}
}
