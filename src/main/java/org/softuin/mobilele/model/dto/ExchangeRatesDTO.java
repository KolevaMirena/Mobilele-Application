package org.softuin.mobilele.model.dto;


import java.math.BigDecimal;
import java.util.Map;

//{
//        "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
//        "license": "https://openexchangerates.org/license",
//        "timestamp": 1723712400,
//        "base": "USD",
//        "rates": {
//        "BGN": 1.776065,
//        "EUR": 0.908601
//        }
//        }
public record ExchangeRatesDTO(String base,
                               Map<String, BigDecimal> rates){

}
