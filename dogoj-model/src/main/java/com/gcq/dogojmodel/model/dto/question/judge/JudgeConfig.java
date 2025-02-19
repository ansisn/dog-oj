package com.gcq.dogojmodel.model.dto.question.judge;

import lombok.Data;

@Data
public class JudgeConfig {

    /**
     * 时间限制
     */
    private Long timeLimit;

    /**
     * 内存限制
     */
    private Long memoryLimit;

    /**
     * 栈限制
     */
    private String stackLimit;
}
