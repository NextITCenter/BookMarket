package kr.or.nextit.bookmarket.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class HitsAspect {
    private final BoardMapper mapper;

    @AfterReturning(value = "execution(* kr..board.BoardService.selectBoard(..)) && args(no)", returning = "board")
    public void afterReturning(JoinPoint joinPoint, BoardVO board, long no) {
        // JoinPoint는 호출된 메소드 정보를 가져올 수 있는 객체
        log.debug("board: {}", board);
        mapper.updateHits(no);
    }
}
