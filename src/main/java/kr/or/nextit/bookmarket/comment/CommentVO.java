package kr.or.nextit.bookmarket.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CommentVO {
    private int id;
    private long boardNo;
    private String content;
    private String writer;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime registerDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime modifiedDate;
}
