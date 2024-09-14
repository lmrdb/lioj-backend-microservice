package com.yupi.lioj.judge.codesandbox.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.lioj.common.ErrorCode;
import com.yupi.lioj.exception.BusinessException;
import com.yupi.lioj.judge.codesandbox.CodeSandBox;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱(实际调用接口的沙箱)
 */
public class RemoteCodeSandBox implements CodeSandBox {

    //定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "lifeiyu";

    private static final String AUTH_REQUEST_SECRET = "hantianzun";


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        String url="http://localhost:8000/executeCode";
        String dUrl="http://118.25.27.85:8000/executeCodeInDocker";
        String rUrl="http://118.25.27.85:8000/executeCode";
        String tUrl="http://192.168.8.128:8000/executeCodeInDocker";
        String json= JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if(StringUtils.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,
                    "executeCode remoteSandBox error,message="+responseStr);
        }

        System.out.println("远程代码沙箱");
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }


}
