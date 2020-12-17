package com.tencent.oa.servicebase.exception;

import com.tencent.oa.commonutils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult error(Exception e){
        e.printStackTrace();
        return JsonResult.failed().message("执行了全局异常处理");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public JsonResult error(GuliException e){
        e.printStackTrace();
        return JsonResult.failed().message(e.getMsg()).code(e.getCode());
    }


}
