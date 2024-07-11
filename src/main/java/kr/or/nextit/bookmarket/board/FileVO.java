package kr.or.nextit.bookmarket.board;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class FileVO {
    private int id;
    private long boardNo;
    private String filePath;
    private String fileName;
    private String originalName;
    private long fileSize;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registerDate;
}
