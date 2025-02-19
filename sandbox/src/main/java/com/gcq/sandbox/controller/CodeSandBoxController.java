package com.gcq.sandbox.controller;

import com.gcq.sandbox.JavaDockerCodeBox;
import com.gcq.sandbox.JavaNativeCodeSandBox;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import com.gcq.sandbox.old.JavaNativeCodeBoxOld;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class CodeSandBoxController {
    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";


    @Resource
    private JavaDockerCodeBox javaDockerCodeBox;

    @Resource
    private JavaNativeCodeSandBox javaNativeCodeSandBox;


    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest, HttpServletRequest request,
                                    HttpServletResponse response) {
        // 基本的认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        return javaNativeCodeSandBox.executeCode(executeCodeRequest);
    }

}
