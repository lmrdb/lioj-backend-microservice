package com.yupi.lioj.judge.codesandbox;

import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeResponse;


/**
 * 代码沙箱接口定义
 */
public interface CodeSandBox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
