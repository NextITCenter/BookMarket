package kr.or.nextit.bookmarket.common;

import kr.or.nextit.bookmarket.comment.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

//    @After("execution(public * kr..comment.CommentService.insertComment(..) )")
//    public void logAfter() {
//        log.debug("====================CommnetService 의 insertComment()메소드 호출 후 출력된 로그");
//    }
//    @Before("execution(public * kr..comment.CommentService.insertComment(..) )")
//    public void logBefore() {
//        log.debug("====================CommentService의 insertComment() 메소드가 호출 되기 전 출력");
//    }

    @Around("execution(public * kr..comment.CommentService.insertComment(..) )")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("====================CommentService의 insertComment() 메소드가 호출 되기 전 출력");
        Object proceed = pjp.proceed();
        log.debug("commentVO: {}", proceed);

        log.debug("====================CommnetService 의 insertComment()메소드 호출 후 출력된 로그");
        return proceed;
    }
}
