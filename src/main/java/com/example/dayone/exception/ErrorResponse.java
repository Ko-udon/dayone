package com.example.dayone.exception;

import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

@Data
@Builder
public class ErrorResponse {
  private int code;
  private String message;
}
