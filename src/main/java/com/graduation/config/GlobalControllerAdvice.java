package com.graduation.config;

import com.graduation.util.AuthException;
import com.graduation.util.Result;
import com.graduation.util.Status;
import com.graduation.util.TokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(TokenException.class)
    public Result handle(TokenException exception){
        return Result.fail(Status.TOKEN_ERROR.getCode(),Status.TOKEN_ERROR.getMsg());
    }
    @ExceptionHandler(AuthException.class)
    public Result handle(AuthException exception){
        return Result.fail(Status.NO_AUTH.getCode(),Status.NO_AUTH.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handle(RuntimeException exception){
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }

}
