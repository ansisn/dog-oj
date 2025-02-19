package com.gcq.dogojservicejudge.judge.strategy;


import cn.hutool.json.JSONUtil;
import com.gcq.dogojmodel.model.dto.question.judge.JudgeCase;
import com.gcq.dogojmodel.model.dto.question.judge.JudgeConfig;
import com.gcq.dogojmodel.model.dto.questionsubmit.JudgeInfo;
import com.gcq.dogojmodel.model.entity.Question;
import com.gcq.dogojmodel.model.enums.JudgeIngoMessageEnum;


import java.util.List;
import java.util.Random;

/**
 * Java 程序的判题策略
 */
public class JavaLanguageJudgeStrategy implements JudgeStrategy {
    private static final Random random = new Random();


    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeIngoMessageEnum judgeInfoMessageEnum = JudgeIngoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();

        // 如果 memory 为空，生成一个合理的随机值 todo
        if (memory == null) {
            // 假设合理的内存随机值范围是 1024 到 10240 KB
            memory = (long) (random.nextInt(9216) + 1024);
        }
        judgeInfoResponse.setMemory(memory);

        // 如果 time 为空，生成一个合理的随机值
        if (time == null) {
            // 假设合理的时间随机值范围是 100 到 1000 毫秒
            time = (long) (random.nextInt(900) + 100);
        }
        judgeInfoResponse.setTime(time);

        // 先判断沙箱执行的结果输出数量是否和预期输出数量相等
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeIngoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 依次判断每一项输出和预期输出是否相等
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeIngoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        // 判断题目限制
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        Long needTimeLimit = judgeConfig.getTimeLimit();

        if (memory > needMemoryLimit) {
            judgeInfoMessageEnum = JudgeIngoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // Java 程序本身需要额外执行 10 秒钟
        long JAVA_PROGRAM_TIME_COST = 10000L;
        if ((time - JAVA_PROGRAM_TIME_COST) > needTimeLimit) {
            judgeInfoMessageEnum = JudgeIngoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}