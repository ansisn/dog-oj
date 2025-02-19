package com.gcq.dogojserviceclient.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gcq.dogojmodel.model.entity.Question;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author guochuqu
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2025-01-08 22:20:44
*/
@FeignClient(name = "dogOj-service-question", path = "/api/question/inner")
public interface QuestionFeignClient  {

    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);

    @PostMapping("/update")
    boolean updateQuestionById(@RequestBody Question question);


    @GetMapping("/questionSubmit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);


    @PostMapping("/questionSubmit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);


}
