package org.softuin.mobilele.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuin.mobilele.model.dto.ExchangeRatesDTO;
import org.softuin.mobilele.model.entity.ExchangeRateEntity;
import org.softuin.mobilele.repository.ExchangeRateRepository;
import org.softuin.mobilele.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {


    private static Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    private final ExchangeRateRepository exchangeRateRepository;

    public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }


    @Override
    public void refreshRate(ExchangeRatesDTO exchangeRatesDTO) {

        LOGGER.info("Exchange rates received");
        BigDecimal BGN_TO_USD = getExchangeRate(exchangeRatesDTO, "BGN", "USD").orElse(null);
        BigDecimal BGN_TO_EUR = getExchangeRate(exchangeRatesDTO, "BGN", "EUR").orElse(null);

        if(BGN_TO_USD != null){
            ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
            exchangeRateEntity.setCurrency("USD");
            exchangeRateEntity.setRate(BGN_TO_USD);

            exchangeRateRepository.save(exchangeRateEntity);
        }else{
            LOGGER.error("Enable to get exchange rate for BGN to USD");
        }


        if(BGN_TO_EUR != null){
            ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
            exchangeRateEntity.setCurrency("EUR");
            exchangeRateEntity.setRate(BGN_TO_EUR);

            exchangeRateRepository.save(exchangeRateEntity);
        }else{
            LOGGER.error("Enable to get exchange rate for BGN to EUR");
        }

        LOGGER.info("Exchange rates updated");

    }


    private static Optional<BigDecimal> getExchangeRate(ExchangeRatesDTO exchangeRatesDTO,
                                                        String from,
                                                        String to) {

        Objects.requireNonNull(from, "From could not be null.");
        Objects.requireNonNull(to, "To could not be null.");

        //e.g. --> USD-->USD, or BGN-->BGN
        if (Objects.equals(from, to)) {
            return Optional.of(BigDecimal.ONE);
        }

        if (from.equals(exchangeRatesDTO.base())) {

            if (exchangeRatesDTO.rates().containsKey(to)) {
                return Optional.of(exchangeRatesDTO.rates().get(to));
            }


        } else if (Objects.equals(to, exchangeRatesDTO.base())) {

            if (exchangeRatesDTO.rates().containsKey(from)) {
                return Optional.of(BigDecimal.ONE.divide(exchangeRatesDTO.rates().get(from), 3, RoundingMode.DOWN));
            }


        } else if (exchangeRatesDTO.rates().containsKey(from) && exchangeRatesDTO.rates().containsKey(to)) {

            return Optional.of(exchangeRatesDTO.rates().get(to).divide(exchangeRatesDTO.rates().get(from), 3, RoundingMode.DOWN));

        }

        return Optional.empty();
    }

}

