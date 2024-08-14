package org.softuin.mobilele.model.dto;

import org.softuin.mobilele.model.enums.EngineEnum;
import org.softuin.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public record CreateOfferDTO(
      String description,
      Long modelId,
      EngineEnum engine,
      TransmissionEnum transmission,
      String imageUrl,
      Integer mileage,

      BigDecimal price,
      Integer year

) {

}
