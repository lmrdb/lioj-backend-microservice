package com.yupi.lioj.judge.strategy;


import com.yupi.lioj.model.dto.question.JudgeCase;
import com.yupi.lioj.judge.codesandbox.model.JudgeInfo;
import com.yupi.lioj.model.entity.Question;
import com.yupi.lioj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数)
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;

}
