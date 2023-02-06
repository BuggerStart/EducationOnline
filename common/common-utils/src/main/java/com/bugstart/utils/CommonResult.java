package com.bugstart.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bugstart
 * @create 2023-01-25 22:34
 */
@Data
public class CommonResult {
    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("响应状态码")
    private Integer code;

    @ApiModelProperty("响应状态")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String,Object> data=new HashMap<String,Object>();

    private CommonResult(){}
    public static CommonResult ok(){
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(true);
        commonResult.setCode(ResultCode.SUCCESS);
        commonResult.setMessage("成功");
        return commonResult;
    }
    public static CommonResult error(){
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setCode(ResultCode.ERROR);
        commonResult.setMessage("失败");
        return commonResult;
    }
    public CommonResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public CommonResult code(Integer code){
        this.setCode(code);
        return this;
    }
    public CommonResult message(String message){
        this.setMessage(message);
        return this;
    }
    public CommonResult data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public CommonResult data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}

