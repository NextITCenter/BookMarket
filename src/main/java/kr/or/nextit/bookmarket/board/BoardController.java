package kr.or.nextit.bookmarket.board;

import kr.or.nextit.bookmarket.common.PaginationInfo;
import kr.or.nextit.bookmarket.common.SearchVO;
import kr.or.nextit.bookmarket.login.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {
    private BoardService service;
    // 생성자 주입방식을 명시적으로 써주면 자동으로 스프링 빈이 주입된다.(DI)
    public BoardController(BoardService service) {
        this.service = service;
    }

    @RequestMapping(value = "/boards", method = RequestMethod.GET)
    // Model: 응답페이지에 데이터를 전달할 때 사용
    public String selectBoards(SearchVO search, @RequestParam(value = "requestPageNo", defaultValue = "1") int requestPageNo, Model model) {
        // 전체 페이지 갯수 가져오기
        int totalCount = service.selectBoardsTotalCount(search);
        // 페이지 정보 만들기
        PaginationInfo info = new PaginationInfo();
        info.setCurrentPageNo(requestPageNo);
        // 한 페이지당 보여줄 게시글 갯수
        info.setRecordCountPerPage(3);
        // 한 페이지에 보여줄 페이지 갯수
        info.setPageSize(3);
        // 전체 레코드(게시글) 갯수
        info.setTotalRecordCount(totalCount);

        // 페이징 처리된 게시글 목록을 가져오기 위해서
        // searchVO에 처음글인덱스와 마지막 글 인덱스를 저장
        search.setFirstRecordIndex(info.getFirstRecordIndex());
        search.setLastRecordIndex(info.getLastRecordIndex());

        // 페이징 된 데이터 가져오기
        List<BoardVO> boards = service.selectBoards(search);
        model.addAttribute("boards", boards);
        model.addAttribute("pagination", info);
        return "board/list";
    }

    @GetMapping("/boards/view")
    public String selectBoard(Model model, long no) {
        BoardVO board = service.selectBoard(no);
        model.addAttribute("board", board);
        return "board/view";
    }

    @GetMapping("/boards/insert")
    public String insertBoard() {
        return "board/insert";
    }

    @PostMapping("/boards/insert")
    // @ModelAttribute: 커맨드객체라고 한다.
    public String insertBoard(@ModelAttribute BoardVO board, HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        String writer = member.getEmail();
        board.setWriter(writer);
        service.insertBoard(board);
        return "redirect:/boards";
    }

    @GetMapping("/boards/update")
    public String updateBoard(Model model, @RequestParam long no) {
        BoardVO board = service.selectBoard(no);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/boards/update")
    public String updateBoard(@ModelAttribute BoardVO board, HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        String writer = member.getEmail();
        board.setWriter(writer);

        service.updateBoard(board);
        return "redirect:/boards";
    }

    @GetMapping("/boards/delete")
    public String deleteBoard(long no) {
        service.deleteBoard(no);
        return "redirect:/boards";
    }
}
