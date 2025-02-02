package com.gcq.sandbox.controller;

import com.gcq.sandbox.JavaDockerCodeBox;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/")
public class CodeSandBoxController {

    @Resource
    private JavaDockerCodeBox javaDockerCodeBox;


    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数不能为空");
        }
        return javaDockerCodeBox.executeCode(executeCodeRequest);

    }

}
