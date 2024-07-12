package kr.or.nextit.bookmarket.comment;

import kr.or.nextit.bookmarket.board.CommentVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    // 댓글(comment) 은 Ajax 방식으로 처리
    @PostMapping("/comments/new")
    public String insertComment(CommentVO comment) {

        return "";
    }
}
