package com.example.dayone.persist.scraper;

import com.example.dayone.persist.model.Company;
import com.example.dayone.persist.model.ScrapedResult;
import org.springframework.context.annotation.Bean;

public interface Scraper {
  Company scrapCompanyByTicker(String ticker);
  ScrapedResult scrap(Company company);

}
