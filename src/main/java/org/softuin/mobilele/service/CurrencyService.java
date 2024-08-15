package org.softuin.mobilele.service;

import org.softuin.mobilele.model.dto.ExchangeRatesDTO;

public interface CurrencyService {
    void refreshRate(ExchangeRatesDTO exchangeRatesDTO);
}
