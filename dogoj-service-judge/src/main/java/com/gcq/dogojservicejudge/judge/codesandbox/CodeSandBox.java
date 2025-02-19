package com.gcq.dogojservicejudge.judge.codesandbox;


import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeRequest;
import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeResponse;

public interface CodeSandBox {


    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
