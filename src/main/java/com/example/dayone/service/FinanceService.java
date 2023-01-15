package com.example.dayone.service;

import com.example.dayone.persist.entity.model.Company;
import com.example.dayone.persist.entity.model.Dividend;
import com.example.dayone.persist.entity.CompanyEntity;
import com.example.dayone.persist.entity.DividendEntity;
import com.example.dayone.persist.entity.model.ScrapedResult;
import com.example.dayone.persist.entity.model.constants.CacheKey;
import com.example.dayone.repository.CompanyRepository;
import com.example.dayone.repository.DividendRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FinanceService {

  private final CompanyRepository companyRepository;
  private final DividendRepository dividendRepository;

  @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
  public ScrapedResult getDividendByCompanyName(String companyName) {
    log.info("search company -> " + companyName);

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
        .map(e -> new Dividend(e.getDate(), e.getDividend()))
        .collect(Collectors.toList());

    return new ScrapedResult(new Company(company.getTicker(), company.getName()),
        dividends);
  }

}
