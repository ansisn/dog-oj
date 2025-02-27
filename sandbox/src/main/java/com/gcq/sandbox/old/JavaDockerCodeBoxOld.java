package com.gcq.sandbox.old;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.gcq.sandbox.judge.codesandbox.CodeSandBox;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteMessage;
import com.gcq.sandbox.judge.codesandbox.model.JudgeInfo;
import com.gcq.sandbox.utils.ProcessUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class JavaDockerCodeBoxOld implements CodeSandBox {
    public static final String GLOBAL_CODE_DIR_NAME =  "tmpCode";

    public static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    public static void main(String[] args) {
        JavaNativeCodeBoxOld javaNativeCodeBox = new JavaNativeCodeBoxOld();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2","2 3"));
        String testCode = ResourceUtil.readStr("testCode/Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(testCode);
        executeCodeRequest.setLanguage("java");
        javaNativeCodeBox.executeCode(executeCodeRequest);
    }


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();
        List<String> inputList = executeCodeRequest.getInputList();
        String userDir = System.getProperty("user.dir");
        String globalCodePathName =userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        //判定全局代码目录是否存在
        if (FileUtil.exist(globalCodePathName)){
            FileUtil.mkdir(globalCodePathName);
        }
        //把用户代码隔离存储
        String userCodePathName = globalCodePathName + File.separator + UUID.randomUUID().toString();
        String userCodeParentPath = userCodePathName + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodeParentPath, StandardCharsets.UTF_8);
        //1.把用户的代码保存为文件
        //2.编译代码，得到class文件

        String compileCmd = String.format("javac -encoding utf-8 %s", userCodeFile.getAbsolutePath());
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(compileCmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProcessUtils.runProcessAndGetMessage(process,"编译");


        //3.执行代码，得到输出结果






        return null;
    }
}
