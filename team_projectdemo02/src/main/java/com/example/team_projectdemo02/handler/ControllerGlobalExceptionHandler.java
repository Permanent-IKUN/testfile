package com.example.team_projectdemo02.handler;

import com.example.team_projectdemo02.model.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class ControllerGlobalExceptionHandler {
    /*
    * 默认异常拦截处理
    * @param e 异常对象
    * @return 处理返回
    */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO<?> handle(Exception e){
        log.error("<<<ExceptionHandle>>:" + e.getMessage());
        return ResponseVO.FAIL(500, e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseVO<?> bindhandle(org.springframework.validation.BindException e){
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable
                ::getDefaultMessage).collect(Collectors.joining());
        log.debug(message);
        return ResponseVO.FAIL(500,message);
    }
    /*
    * 参数校验异常拦截处理
    * @param e 异常对象
    * @return 处理返回
    */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseVO<?> runtimeExceptionHandle(RuntimeException e){
        log.error("<<<ExceptionHandle>>:" + e.getMessage());
        return ResponseVO.FAIL(500, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVO<?> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable
                ::getDefaultMessage).collect(Collectors.joining());
        log.debug("<<<methodArgumentNotValidExceptionHandle>>>:" + message);
        return ResponseVO.FAIL(500,message);
    }

    /*请求body为空异常拦截处理
    * @return处理返回
    */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseVO<?> httpMessageNotReadableExceptionHandle(){
        return ResponseVO.FAIL(500,"请求body为空");
    }

}
