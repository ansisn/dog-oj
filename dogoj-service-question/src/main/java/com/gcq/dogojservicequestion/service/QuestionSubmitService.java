package com.gcq.dogojservicequestion.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcq.dogojmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.gcq.dogojmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojmodel.model.entity.User;
import com.gcq.dogojmodel.model.vo.QuestionSubmitVO;


/**
* @author guochuqu
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2025-01-08 22:20:53
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    Wrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
