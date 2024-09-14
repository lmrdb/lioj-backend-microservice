package com.yupi.lioj.judge;

import com.yupi.lioj.model.entity.QuestionSubmit;
import com.yupi.lioj.model.vo.QuestionSubmitVO;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
