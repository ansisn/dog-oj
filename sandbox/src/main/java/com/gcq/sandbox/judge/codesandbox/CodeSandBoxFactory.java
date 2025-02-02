package com.gcq.sandbox.judge.codesandbox;


import com.gcq.sandbox.judge.codesandbox.impl.ExampleCodeSandBox;
import com.gcq.sandbox.judge.codesandbox.impl.RemoteCodeSandBox;
import com.gcq.sandbox.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * @Author: guo
 *  简单工厂模式
 */
public class CodeSandBoxFactory {


    public static CodeSandBox createCodeSandbox(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "third":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
