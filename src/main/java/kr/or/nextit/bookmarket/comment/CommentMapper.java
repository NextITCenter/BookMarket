package kr.or.nextit.bookmarket.comment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void insertComment(CommentVO comment);
    CommentVO selectComment(int id);
    void deleteComment(long boardNo);
}
