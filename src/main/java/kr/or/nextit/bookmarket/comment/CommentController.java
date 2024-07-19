package kr.or.nextit.bookmarket.comment;

import kr.or.nextit.bookmarket.member.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService service;

    // 댓글(comment) 등록은 Ajax(Asynchronous Javascript And XML) 방식으로 처리
    // 화면(View)이 아닌 데이터만 응답할 경우 @ResponseBody를 추가해준다.
    @ResponseBody
    @PostMapping("/comments/new")
    public CommentVO insertComment(@RequestBody CommentVO comment, HttpSession session) {
        // 세션에서 로그인한 사용자의 이메일 정보를 가져와서 comment의 writer 컬럼에 넣어준다.
        MemberVO member = (MemberVO) session.getAttribute("member");
        comment.setWriter(member.getEmail());
        return service.insertComment(comment);
    }
}
