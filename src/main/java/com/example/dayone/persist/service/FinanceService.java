package com.example.dayone.persist.service;

import com.example.dayone.persist.entity.CompanyEntity;
import com.example.dayone.persist.entity.DividendEntity;
import com.example.dayone.persist.model.Company;
import com.example.dayone.persist.model.Dividend;
import com.example.dayone.persist.model.ScrapedResult;
import com.example.dayone.persist.repository.CompanyRepository;
import com.example.dayone.persist.repository.DividendRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinanceService {

  private final CompanyRepository companyRepository;
  private final DividendRepository dividendRepository;

  /*@Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)*/
  public ScrapedResult getDividendByCompanyName(String companyName) {
    // 1. 회사명을 기준으로 회사 정보를 조회
    CompanyEntity company = this.companyRepository.findByName(companyName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명 입니다."));
    // 2. 조회된 회사 ID 로 배당금 정보 조회
    List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(
        company.getId());

    // 3. 결과 조합 후 반환
    /*List<Dividend> dividends = new ArrayList<>();
    for (var entity : dividendEntities) {
      dividends.add(Dividend.builder()
          .data(entity.getDate())
          .dividend(entity.getDividend())
          .build());
    }
    아래 방법도 가능!
    */

    List<Dividend> dividends = dividendEntities.stream()
        .map(e -> Dividend.builder()
            .data(e.getDate())
            .dividend(e.getDividend())
            .build())
        .collect(Collectors.toList());

    return new ScrapedResult(Company.builder()
        .ticker(company.getTicker())
        .name(company.getName())
        .build(), dividends);
  }

}
