package kr.or.nextit.bookmarket.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentVO {
    private int id;
    private long boardNo;
    private String content;
    private String writer;
    private LocalDateTime registerDate;
    private LocalDateTime modifiedDate;
}
