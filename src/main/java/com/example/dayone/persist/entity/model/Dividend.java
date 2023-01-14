package com.example.dayone.persist.entity.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.asm.Advice.Local;

@Data
@Builder
public class Dividend {

  private LocalDateTime data;
  private String dividend;


}
