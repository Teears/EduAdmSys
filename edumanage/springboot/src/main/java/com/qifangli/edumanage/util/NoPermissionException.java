package com.qifangli.edumanage.util;

import com.qifangli.edumanage.util.result.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@ControllerAdvice
public class NoPermissionException {

    @ExceptionHandler(UnauthorizedException.class)
    public Result handleShiroException(Exception ex) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("not authorized");
        return result;
    }
}