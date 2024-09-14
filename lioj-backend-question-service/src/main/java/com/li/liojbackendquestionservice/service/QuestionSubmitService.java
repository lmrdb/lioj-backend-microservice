package com.yupi.lioj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.lioj.model.dto.question.QuestionQueryRequest;
import com.yupi.lioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.lioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.lioj.model.entity.Question;
import com.yupi.lioj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.lioj.model.entity.User;
import com.yupi.lioj.model.vo.QuestionSubmitVO;
import com.yupi.lioj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 19799
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-08-29 16:26:28
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
