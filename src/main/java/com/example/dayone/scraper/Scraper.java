package com.example.dayone.scraper;

import com.example.dayone.persist.entity.model.Company;
import com.example.dayone.persist.entity.model.ScrapedResult;

public interface Scraper {
  Company scrapCompanyByTicker(String ticker);
  ScrapedResult scrap(Company company);

}
