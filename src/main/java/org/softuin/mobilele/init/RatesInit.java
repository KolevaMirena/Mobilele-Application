package org.softuin.mobilele.init;

import org.softuin.mobilele.config.OpenExchangeRateConfig;
import org.softuin.mobilele.model.dto.ExchangeRatesDTO;
import org.softuin.mobilele.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;



@Component
public class RatesInit implements CommandLineRunner {


    private final OpenExchangeRateConfig openExchangeRateConfig;
    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    public RatesInit(OpenExchangeRateConfig openExchangeRateConfig, RestTemplate restTemplate, CurrencyService currencyService) {

        this.openExchangeRateConfig = openExchangeRateConfig;
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(openExchangeRateConfig.isEnabled()){
        Map<String, String> requestParams = Map.of("app_id", openExchangeRateConfig.getAppId(),
                    "symbols", String.join(",", openExchangeRateConfig.getSymbols()));

        String openExchangeRateUrl = openExchangeRateConfig.getSchema() +
                "://" +
                openExchangeRateConfig.getHost() +
                openExchangeRateConfig.getPath() +
                "?app_id={app_id}&symbols={symbols}";

        ExchangeRatesDTO exchangeRatesDTO = restTemplate.getForObject(openExchangeRateUrl, ExchangeRatesDTO.class, requestParams);

        currencyService.refreshRate(exchangeRatesDTO);
        }
    }
}
