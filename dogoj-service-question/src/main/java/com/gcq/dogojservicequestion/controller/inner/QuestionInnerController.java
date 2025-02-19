package com.gcq.dogojservicequestion.controller.inner;


import com.gcq.dogojmodel.model.entity.Question;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojserviceclient.service.QuestionFeignClient;
import com.gcq.dogojservicequestion.service.QuestionService;
import com.gcq.dogojservicequestion.service.QuestionSubmitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {
    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;




    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId){
     return questionService.getById(questionId);
    }

    @PostMapping("/update")
    public boolean updateQuestionById(@RequestBody Question question){
        return questionService.updateById(question);
    }


    @GetMapping("/questionSubmit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    }


    @PostMapping("/questionSubmit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit){
        return questionSubmitService.updateById(questionSubmit);
    }

}
