package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;
import lombok.ToString;

@ToString
public enum  CmsCode implements ResultCode {
    CMS_ADDPAGE_EXISTS(false,24001,"页面已存在！"),//操作结果
     CMS_GENERATEHTML_TEMPLATEISNULL (false,24001,"数据或页面模版不存在！！"),
    CMS_COONETION_TIMOUT(false,24001,"数据库连接超时");
    boolean success; //操作代码
    int code;//提示信息
    String message;
    private CmsCode(boolean success, int code, String message){ this.success = success; this.code = code; this.message = message; }

    @Override public boolean success() { return success; }@Override public int code() { return code; }@Override public String message() { return message; }
}
