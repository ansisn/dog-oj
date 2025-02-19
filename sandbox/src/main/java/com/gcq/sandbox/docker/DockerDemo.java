package com.gcq.sandbox.docker;

import cn.hutool.core.io.resource.ResourceUtil;
import com.gcq.sandbox.JavaDockerCodeBox;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DockerDemo {

    public static void main(String[] args) throws InterruptedException {

        JavaDockerCodeBox javaNativeCodeBox = new JavaDockerCodeBox();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2","2 3"));
        String testCode = ResourceUtil.readStr("testCode/Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(testCode);
        executeCodeRequest.setLanguage("java");
        ExecuteCodeResponse executeCodeResponse = javaNativeCodeBox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);

    }

}
