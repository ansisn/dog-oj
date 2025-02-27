package com.gcq.sandbox;

import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeRequest;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteCodeResponse;
import com.gcq.sandbox.judge.codesandbox.model.ExecuteMessage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class JavaNativeCodeSandBox extends AbstractCodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
