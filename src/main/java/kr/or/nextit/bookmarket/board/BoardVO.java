package kr.or.nextit.bookmarket.board;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardVO {
	private long no;
	private String title;
	private String content;
	private String writer;
	private String name;
	private int hits;
	private LocalDateTime registerDate;
	private LocalDateTime modifiedDate;
	private List<FileVO> fileList;
	private List<CommentVO> commentList;
}
