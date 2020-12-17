package com.tencent.oa.commonutils;

import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("业务数据")
    private Map<String,Object> data = new HashMap<>();

    private JsonResult(){};

    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setFlag(true);
        jsonResult.setCode(ResultCode.SUCCESS);
        jsonResult.setMessage("成功");
        return jsonResult;
    }

    public static JsonResult failed(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setFlag(false);
        jsonResult.setCode(ResultCode.FAILED);
        jsonResult.setMessage("失败");
        return jsonResult;
    }

    public JsonResult flag(boolean flag){
        this.setFlag(flag);
        return this;
    }

    public JsonResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public JsonResult message(String message){
        this.setMessage(message);
        return this;
    }

    public JsonResult data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public JsonResult data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setFlag(boolean flag) {
        this.success = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
