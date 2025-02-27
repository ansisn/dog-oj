package com.gcq.sandbox.judge.codesandbox;


import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandBox {


    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
