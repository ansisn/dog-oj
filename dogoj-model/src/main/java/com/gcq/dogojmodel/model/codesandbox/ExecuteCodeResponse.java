package com.gcq.dogojmodel.model.codesandbox;


import com.gcq.dogojmodel.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExecuteCodeResponse {


    private List<String> outputList;

   private String message;

   private String status;

   private JudgeInfo judgeInfo;


}
