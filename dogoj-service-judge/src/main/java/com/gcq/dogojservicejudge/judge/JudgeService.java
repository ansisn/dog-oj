package com.gcq.dogojservicejudge.judge;


import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojmodel.model.entity.User;

public interface JudgeService {


  QuestionSubmit doJudge(long questionSubmitId , User loginUser);
}
