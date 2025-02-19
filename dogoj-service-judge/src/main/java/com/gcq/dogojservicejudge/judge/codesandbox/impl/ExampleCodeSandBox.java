package com.gcq.dogojservicejudge.judge.codesandbox.impl;


import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeRequest;
import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeResponse;
import com.gcq.dogojservicejudge.judge.codesandbox.CodeSandBox;
import org.springframework.stereotype.Service;

@Service
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();

        return null;
    }
}
