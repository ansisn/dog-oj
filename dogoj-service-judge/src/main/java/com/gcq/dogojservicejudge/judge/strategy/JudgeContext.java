package com.gcq.dogojservicejudge.judge.strategy;


import com.gcq.dogojmodel.model.dto.question.judge.JudgeCase;
import com.gcq.dogojmodel.model.dto.questionsubmit.JudgeInfo;
import com.gcq.dogojmodel.model.entity.Question;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
