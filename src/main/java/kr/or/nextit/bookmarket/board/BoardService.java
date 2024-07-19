package kr.or.nextit.bookmarket.board;

import java.util.List;

import kr.or.nextit.bookmarket.comment.CommentMapper;
import kr.or.nextit.bookmarket.common.SearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardMapper mapper;
	private final FileMapper fileMapper;
	private final CommentMapper commentMapper;

	public int selectBoardsTotalCount(SearchVO search) {
		return mapper.selectBoardsTotalCount(search);
	}

	public List<BoardVO> selectBoards(SearchVO search) {
		return mapper.selectBoards(search);
	}
	@Transactional
	public BoardVO selectBoard(long no) {
//		mapper.updateHits(no);
		return mapper.selectBoard(no);
	}
	@Transactional
	public int insertBoard(BoardVO board) {
		// insert가 끝나면 board 인스턴스에는 no 필드에 방금 insert한 데이터의
		// pk값이 들어가 있다.
		int insertedValue = mapper.insertBoard(board);
		List<FileVO> fileList = board.getFileList();
		if (!fileList.isEmpty()) {
			fileList.forEach(f -> f.setBoardNo(board.getNo()));
			fileMapper.saveFiles(fileList);
		}
		return insertedValue;
	}
	
	public int updateBoard(BoardVO board) {
		return mapper.updateBoard(board);
	}

	@Transactional
	public int deleteBoard(long no) {
//		fileMapper.deleteFile(no);
//		commentMapper.deleteComment(no);
		return mapper.deleteBoard(no);
	}

	public FileVO selectFile(int id) {
		return fileMapper.selectFile(id);
	}
}










