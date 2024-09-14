package com.yupi.lioj.judge.codesandbox.impl;

import com.yupi.lioj.judge.codesandbox.CodeSandBox;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现有的代码沙箱）
 */
public class ThirdPartyCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
