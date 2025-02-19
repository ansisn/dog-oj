package com.gcq.dogojservicejudge.judge.strategy;


import com.gcq.dogojmodel.model.dto.questionsubmit.JudgeInfo;

public interface JudgeStrategy {

    JudgeInfo doJudge(JudgeContext judgeContext);

}
