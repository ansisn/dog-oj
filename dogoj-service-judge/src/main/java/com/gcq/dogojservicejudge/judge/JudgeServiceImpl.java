package com.gcq.dogojservicejudge.judge;

import cn.hutool.json.JSONUtil;

import com.gcq.dogojcommon.common.ErrorCode;
import com.gcq.dogojcommon.exception.BusinessException;
import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeRequest;
import com.gcq.dogojmodel.model.codesandbox.ExecuteCodeResponse;
import com.gcq.dogojmodel.model.dto.question.judge.JudgeCase;
import com.gcq.dogojmodel.model.dto.questionsubmit.JudgeInfo;
import com.gcq.dogojmodel.model.entity.Question;
import com.gcq.dogojmodel.model.entity.QuestionSubmit;
import com.gcq.dogojmodel.model.entity.User;
import com.gcq.dogojmodel.model.enums.QuestionSubmitStatusEnum;
import com.gcq.dogojserviceclient.service.QuestionFeignClient;
import com.gcq.dogojservicejudge.judge.codesandbox.CodeSandBox;
import com.gcq.dogojservicejudge.judge.codesandbox.CodeSandBoxFactory;
import com.gcq.dogojservicejudge.judge.codesandbox.CodeSandBoxProxy;

import com.gcq.dogojservicejudge.judge.strategy.JudgeContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {



    @Resource
    private QuestionFeignClient questionService;



    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:remote}")
    private String type;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId, User loginUser) {
        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionService.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.JUDGING.getValue());
        boolean update = questionService.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        // 4）调用沙箱，获取到执行结果
        CodeSandBox codeSandbox = CodeSandBoxFactory.createCodeSandbox(type);
        codeSandbox = new CodeSandBoxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
         update = questionService.updateQuestionSubmitById(questionSubmitUpdate);
        //7)更新题目信息
        Question questionUpdate = questionService.getQuestionById(questionId);
        questionUpdate.setSubmitNum(question.getSubmitNum() + 1);
        if (executeCodeResponse.getStatus().equals("success")){
            questionUpdate.setAcceptedNum(question.getAcceptedNum() + 1);
        }
        questionService.updateQuestionById(questionUpdate);
        //8)跟新用户通过率 todo

        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionService.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}

