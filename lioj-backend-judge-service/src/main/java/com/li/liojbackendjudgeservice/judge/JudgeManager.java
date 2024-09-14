package com.yupi.lioj.judge;

import com.yupi.lioj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.lioj.judge.strategy.JavaJudgeStrategy;
import com.yupi.lioj.judge.strategy.JudgeContext;
import com.yupi.lioj.judge.strategy.JudgeStrategy;
import com.yupi.lioj.judge.codesandbox.model.JudgeInfo;
import com.yupi.lioj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext) {

        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy=new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy=new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);

    }
}
