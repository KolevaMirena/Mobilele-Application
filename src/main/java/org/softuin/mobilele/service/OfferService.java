package org.softuin.mobilele.service;


import org.softuin.mobilele.model.dto.CreateOfferDTO;

;import java.util.UUID;

public interface OfferService {

    UUID createOffer(CreateOfferDTO createOfferDTO);
}
