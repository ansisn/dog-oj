package com.gcq.dogojservicejudge.judge;


import com.gcq.dogojmodel.model.dto.questionsubmit.JudgeInfo;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojservicejudge.judge.strategy.DefaultJudgeStrategy;
import com.gcq.dogojservicejudge.judge.strategy.JavaLanguageJudgeStrategy;
import com.gcq.dogojservicejudge.judge.strategy.JudgeContext;
import com.gcq.dogojservicejudge.judge.strategy.JudgeStrategy;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
