package com.li.liojbackendjudgeservice.judge.controller.inner;

import com.li.liojbackendclient.service.QuestionFeignClient;
import com.li.liojbackendmodel.entity.Question;
import com.li.liojbackendmodel.entity.QuestionSubmit;
import com.li.liojbackendquestionservice.service.QuestionService;
import com.li.liojbackendquestionservice.service.QuestionSubmitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 该服务仅内部调用,不是给前端的
 */
@RestController("/inner")
public class QuestionInnerController implements QuestionFeignClient {


    @Resource
    QuestionService questionService;

    @Resource
    QuestionSubmitService questionSubmitService;


    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") Long questionId){
        return questionService.getById(questionId);
    };



    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    };


    @Override
    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit){
        return questionSubmitService.updateById(questionSubmit);
    };



}
