package com.gcq.dogojservicejudge.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import com.gcq.dogojcommon.common.ErrorCode;
import com.gcq.dogojcommon.exception.BusinessException;
import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeRequest;
import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeResponse;
import com.gcq.dogojservicejudge.judge.codesandbox.CodeSandBox;
import org.apache.commons.lang3.StringUtils;

public class RemoteCodeSandBox implements CodeSandBox {

    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:9090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "executeCode remoteSandbox error, message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }

}
