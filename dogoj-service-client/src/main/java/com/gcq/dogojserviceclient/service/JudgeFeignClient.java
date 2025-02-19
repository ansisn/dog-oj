package com.gcq.dogojserviceclient.service;


import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojmodel.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author guochuqu
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2025-01-08 22:20:44
 */
@FeignClient(name = "dogOj-service-judge", path = "/api/judge/inner")
public interface JudgeFeignClient {


  @PostMapping("/do")
  QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId ,@RequestBody User loginUser);
}
