package com.gcq.dogojmodel.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色枚举
 *
 * @author <a ">郭楚锜</a>
 * @from <a >QQ：2798713432</a>
 */
public enum QuestionSubmitStatusEnum {

    // 0-等待判题 1-判题中 2-成功 3-失败
    WAITING("等待判题", 0),
    JUDGING("判题中", 1),
    SUCCESS("成功", 2),
    FAIL("失败", 3);

    private final String text;

    private final Integer value;

    QuestionSubmitStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitStatusEnum questionSubmitStatusEnum(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitStatusEnum anEnum : QuestionSubmitStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }



    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
