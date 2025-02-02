package com.gcq.sandbox.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExecuteMessage {
   private int exitValue;

   private String message;

   private String errorMessage;

   private long time;

   private long memory;
}
