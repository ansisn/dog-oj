package com.gcq.sandbox.judge.codesandbox;


import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CodeSandBoxProxy {

    private CodeSandBox codeSandBox;


    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("executeCodeRequest: {}", executeCodeRequest);
        return codeSandBox.executeCode(executeCodeRequest);

    }
}
