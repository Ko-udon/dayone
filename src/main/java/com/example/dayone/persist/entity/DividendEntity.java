package com.example.dayone.persist.entity;

import com.example.dayone.persist.model.Dividend;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "DIVIDEND")
@Getter
@ToString
@NoArgsConstructor
public class DividendEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long companyId;

  private LocalDateTime date;

  private String dividend;

  public DividendEntity(Long companyId, Dividend dividend) {
    this.companyId = companyId;
    this.date = dividend.getData();
    this.dividend = dividend.getDividend();
  }
}
