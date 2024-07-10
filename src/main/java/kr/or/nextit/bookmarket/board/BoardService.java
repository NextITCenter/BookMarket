package kr.or.nextit.bookmarket.board;

import java.util.List;

import kr.or.nextit.bookmarket.common.SearchVO;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	private final BoardMapper mapper;
	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}
	public int selectBoardsTotalCount(SearchVO search) {
		return mapper.selectBoardsTotalCount(search);
	}

	public List<BoardVO> selectBoards(SearchVO search) {
		return mapper.selectBoards(search);
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










