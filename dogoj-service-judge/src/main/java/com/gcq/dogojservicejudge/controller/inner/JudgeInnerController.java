package com.gcq.dogojservicejudge.controller.inner;

import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojmodel.model.entity.User;
import com.gcq.dogojserviceclient.service.JudgeFeignClient;
import com.gcq.dogojservicejudge.judge.JudgeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId, User loginUser) {
        return judgeService.doJudge(questionSubmitId, loginUser);
    }
}
