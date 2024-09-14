package com.yupi.lioj.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.lioj.common.ErrorCode;
import com.yupi.lioj.exception.BusinessException;
import com.yupi.lioj.judge.codesandbox.CodeSandBox;
import com.yupi.lioj.judge.codesandbox.CodeSandBoxFactory;
import com.yupi.lioj.judge.codesandbox.CodeSandBoxProxy;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.lioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.lioj.judge.strategy.JudgeContext;
import com.yupi.lioj.model.dto.question.JudgeCase;
import com.yupi.lioj.judge.codesandbox.model.JudgeInfo;
import com.yupi.lioj.model.entity.Question;
import com.yupi.lioj.model.entity.QuestionSubmit;
import com.yupi.lioj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.lioj.service.QuestionService;
import com.yupi.lioj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {


    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    JudgeManager judgeManager;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //1）传入题目的id、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit=questionSubmitService.getById(questionSubmitId);
        if(questionSubmit==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question=questionService.getById(questionId);
        if(question==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        //2）如果不为等待状态，则不必反复执行
        if(!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"正在判题");
        }
        //3）更改判题状态为判题中，防止重复执行，并让用户看到状态
        QuestionSubmit questionSubmitUpdate=new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"判题状态更新失败");
        }
        //4)调用代码沙箱，获取执行结果
        CodeSandBox codeSandBox= CodeSandBoxFactory.newInstance(type);
        codeSandBox =new CodeSandBoxProxy(codeSandBox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList= judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeContext judgeContext=new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);


        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        //6)修改数据库的判题结果
        questionSubmitUpdate=new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"判题状态更新失败");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);
        return questionSubmitResult;
    }
}
