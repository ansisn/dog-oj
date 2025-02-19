package com.gcq.dogojservicequestion.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcq.dogojmodel.model.dto.question.QuestionQueryRequest;
import com.gcq.dogojmodel.model.entity.Question;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojmodel.model.vo.QuestionVO;


import javax.servlet.http.HttpServletRequest;

/**
* @author guochuqu
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2025-01-08 22:20:44
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     * @param question
     * @param b
     */
    void validQuestion(Question question, boolean b);

    /**
     * 获取查询条件
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     *
     * @param questionQueryRequest
     * @return
     */
    Wrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

    /**
     * 从 ES 查询
     * @param questionQueryRequest
     * @return
     */
    Page<Question> searchFromEs(QuestionQueryRequest questionQueryRequest);

}
