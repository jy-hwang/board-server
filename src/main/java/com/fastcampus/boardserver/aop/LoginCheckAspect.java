package com.fastcampus.boardserver.aop;

import com.fastcampus.boardserver.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j2
public class LoginCheckAspect {

  @Around("@annotation(com.fastcampus.boardserver.aop.LoginCheck) && @ annotation(loginCheck)")
  public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable {
    HttpSession session = (HttpSession) ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
    String userId = null;
    int idIndex = 0;

    String userType = loginCheck.type().toString();
    switch (userType) {
      case "ADMIN": {
        userId = SessionUtil.getLoginAdminId(session);
        break;
      }
      case "USER": {
        userId = SessionUtil.getLoginMemberId(session);
        break;
      }
    }
    if (userId == null) {
      log.error("{}accountName : {}", proceedingJoinPoint.toString(), userId);
      throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한ID값을 확인해주세요.") {
      };

    }
    Object[] modefiedArgs = proceedingJoinPoint.getArgs();

    if(proceedingJoinPoint != null){
      modefiedArgs[idIndex] = userId;
    }

    return proceedingJoinPoint.proceed(modefiedArgs);
  }
}
