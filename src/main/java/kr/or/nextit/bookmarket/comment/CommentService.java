package kr.or.nextit.bookmarket.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentMapper mapper;

    /**
     * 댓글을 등록 한 뒤 방금 등록한 댓글 아이디를 반환시켜준다.
     * @param comment
     * @return 등록한 댓글 ID
     */
    public CommentVO insertComment(CommentVO comment) {
        mapper.insertComment(comment);
        CommentVO selectComment = mapper.selectComment(comment.getId());
        return selectComment;
    }
}
