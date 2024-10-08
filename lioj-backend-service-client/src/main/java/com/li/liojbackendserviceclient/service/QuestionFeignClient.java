package com.li.liojbackendclient.service;

import com.li.liojbackendmodel.entity.Question;
import com.li.liojbackendmodel.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author 19799
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-08-29 16:25:00
*/
@FeignClient(name = "lioj-backend-question-service",path = "/api/question/inner")
public interface QuestionFeignClient  {



    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);


    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId);

    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);
}
