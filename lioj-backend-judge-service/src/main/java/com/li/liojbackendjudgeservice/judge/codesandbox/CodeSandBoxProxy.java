package com.yupi.lioj.judge.codesandbox;

import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandBoxProxy implements CodeSandBox{

    private final CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("Execute code request: {}", executeCodeRequest);
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("Execute code response: {}", executeCodeResponse);
        return executeCodeResponse;
    }
}
