package com.gcq.sandbox;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.gcq.sandbox.judge.codesandbox.CodeSandBox;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteMessage;
import com.gcq.sandbox.judge.codesandbox.model.JudgeInfo;
import com.gcq.sandbox.utils.ProcessUtils;
import org.apache.commons.lang3.StringUtils;

// 抽象类，定义模板方法和可重写的步骤
public abstract class AbstractCodeSandBox implements CodeSandBox {
    private static final Logger log = Logger.getLogger(AbstractCodeSandBox.class.getName());
    public static final String GLOBAL_CODE_DIR_NAME = "tmpCode";
    public static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    // 模板方法，定义执行代码的整体流程
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();

        // 1. 把用户的代码保存为文件
        File userCodeFile = saveCodeToFile(code);

        // 2. 编译代码，得到 class 文件
        ExecuteMessage compileFileExecuteMessage = compileFile(userCodeFile);
        System.out.println(compileFileExecuteMessage);

        // 3. 执行代码，得到输出结果
        List<ExecuteMessage> executeMessageList = runFile(userCodeFile, inputList);

        // 4. 收集整理输出结果
        ExecuteCodeResponse outputResponse = getOutputResponse(executeMessageList);

        // 5. 文件清理
        boolean b = deleteFile(userCodeFile);
        if (!b) {
            log.log(Level.SEVERE, "deleteFile error, userCodeFilePath = {0}", userCodeFile.getAbsolutePath());
        }
        return outputResponse;
    }


    public File saveCodeToFile(String code) {
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判定全局代码目录是否存在
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        // 把用户代码隔离存储
        String userCodePathName = globalCodePathName + File.separator + UUID.randomUUID().toString();
        String userCodeParentPath = userCodePathName + File.separator + GLOBAL_JAVA_CLASS_NAME;
        return FileUtil.writeString(code, userCodeParentPath, StandardCharsets.UTF_8);
    }


    public ExecuteMessage compileFile(File userCodeFile) {
        String compileCmd = String.format("javac -encoding utf-8 %s", userCodeFile.getAbsolutePath());
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(compileCmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProcessUtils.runProcessAndGetMessage(process, "编译");
    }


    public List<ExecuteMessage> runFile(File userCodeFile, List<String> inputList) {
        String userCodePathName = userCodeFile.getParent();
        ArrayList<ExecuteMessage> executeMessageArrayList = new ArrayList<>();
        for (String inputArgs : inputList) {
            String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s", userCodePathName, inputArgs);
          //  String runCmd = String.format("java -Dfile.encoding=UTF-8 -cp %s;%s -Djava.security.manager=MySecurityManager Main\n", userCodePathName, inputArgs);
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(runCmd);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(process, inputArgs);
            executeMessageArrayList.add(executeMessage);
            System.out.println(executeMessage);
        }
        return executeMessageArrayList;
    }


    public ExecuteCodeResponse getOutputResponse(List<ExecuteMessage> executeMessageList) {
        long maxTime = 0;
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        ArrayList<String> outputList = new ArrayList<>();
        for (ExecuteMessage executeMessage : executeMessageList) {
            String errorMessage = executeMessage.getErrorMessage();
            maxTime = Math.max(maxTime, executeMessage.getTime());
            if (StringUtils.isNotBlank(errorMessage)) {
                executeCodeResponse.setMessage(errorMessage);
                executeCodeResponse.setStatus("error");
                break;
            }
            outputList.add(executeMessage.getMessage());
        }
        if (outputList.size() == executeMessageList.size()) {
            executeCodeResponse.setStatus("success");
        }
        executeCodeResponse.setOutputList(outputList);
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(maxTime);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }


    public boolean deleteFile(File userCodeFile) {
        if (userCodeFile.getParentFile() != null) {
            return FileUtil.del(userCodeFile.getParent());
        }
        return false;
    }
}
