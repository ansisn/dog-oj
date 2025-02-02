package com.gcq.sandbox.judge.codesandbox.impl;


import com.gcq.sandbox.judge.codesandbox.CodeSandBox;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();

        return null;
    }
}
