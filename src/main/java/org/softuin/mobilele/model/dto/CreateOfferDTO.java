package org.softuin.mobilele.model.dto;

import jakarta.validation.constraints.*;
import org.softuin.mobilele.model.enums.EngineEnum;
import org.softuin.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public record CreateOfferDTO (@NotEmpty
                              @Size(min = 5, max = 500)
                              String description,

                                      @Positive
                                      @NotNull
                                      Long modelId,
                                      @NotNull
                                        EngineEnum engine,
                                      @NotNull
                                    TransmissionEnum transmission,
                                      @NotEmpty
                                    String imageUrl,
                                      @Positive
                                      @NotNull
                              Integer mileage,
                                      @Positive
                                      @NotNull
                              BigDecimal price,
                                      @Positive(message = "Year must be positive number!")
                                      @Min(value = 1990)
                                      @NotNull(message = "Year could not be empty!")
                              Integer year){



    public static CreateOfferDTO empty(){
         return  new CreateOfferDTO(null, null, null, null,
                                        null, null, null, null)  ;
     }
}




