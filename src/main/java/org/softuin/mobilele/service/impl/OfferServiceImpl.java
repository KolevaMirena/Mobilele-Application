package org.softuin.mobilele.service.impl;



import org.softuin.mobilele.model.dto.CreateOfferDTO;
import org.softuin.mobilele.repository.OfferRepository;
import org.softuin.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    public OfferServiceImpl(OfferRepository offerRepository){

        this.offerRepository = offerRepository;

    }
    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {

        //TODO

        throw new UnsupportedOperationException("Coming soon!");
    }
}
