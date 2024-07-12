package kr.or.nextit.bookmarket.board;

import kr.or.nextit.bookmarket.common.PaginationInfo;
import kr.or.nextit.bookmarket.common.SearchVO;
import kr.or.nextit.bookmarket.login.MemberVO;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {
    private final BoardService service;
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
    public String insertBoard(@ModelAttribute BoardVO board, List<MultipartFile> files, HttpSession session) throws IOException {
        // 첨부파일 등록(물리적인 위치로 저장)
        // 물리적 위치의 파일명은 UUID를 사용하여 만든다.
        Path path = Paths.get("c:\\", "users", "user", "book", "attachment");
        List<FileVO> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileVO vo = new FileVO();
            String fileName = UUID.randomUUID().toString();
            vo.setFileName(fileName);
            vo.setOriginalName(file.getOriginalFilename());
            vo.setFileSize(file.getSize());
            vo.setFilePath(path.toString());
            fileList.add(vo);

            if (Files.notExists(path)) {
                // 경로가 없으면 원하는 디렉토리 생성
                Files.createDirectory(path);
            }
            // 실제 경로에 첨부파일을 등록
            file.transferTo(Paths.get(path.toString(), fileName));
        }
        
        // 게시글 등록
        MemberVO member = (MemberVO) session.getAttribute("member");
        String writer = member.getEmail();
        board.setWriter(writer);
        board.setFileList(fileList);

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

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable int fileId) throws IOException {
        // ResponseEntity: 응답 객체 => 파일, json, xml
        // ResponseEntity<Resource> => 파일 등등과 같은 리소스를 응답해준다.
        FileVO file = service.selectFile(fileId);

        Path path = Paths.get(file.getFilePath(), file.getFileName());
        // 한글 파일의 경우 정상적으로 파일 이름이 안나오는 경우가 발생한다.
        String filename = URLEncoder.encode(file.getOriginalName(), StandardCharsets.UTF_8);

        FileUrlResource resource = new FileUrlResource(path.toString());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.getFileSize())
                .header(HttpHeaders.PRAGMA, "no-cache")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                .body(resource);
    }
}
