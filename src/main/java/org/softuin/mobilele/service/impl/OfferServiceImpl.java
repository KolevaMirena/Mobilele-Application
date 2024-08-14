package org.softuin.mobilele.service.impl;



import org.softuin.mobilele.model.dto.CreateOfferDTO;
import org.softuin.mobilele.model.entity.ModelEntity;
import org.softuin.mobilele.model.entity.OfferEntity;
import org.softuin.mobilele.repository.ModelRepository;
import org.softuin.mobilele.repository.OfferRepository;
import org.softuin.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository){

        this.offerRepository = offerRepository;

        this.modelRepository = modelRepository;
    }
    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {

        OfferEntity newOffer = map(createOfferDTO);


        ModelEntity model = modelRepository.findById(createOfferDTO.modelId()).orElseThrow(()
                -> new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found"));
        newOffer.setModel(model);

        newOffer = offerRepository.save(newOffer);


       return  newOffer.getUuid();
    }

    private OfferEntity map(CreateOfferDTO createOfferDTO){

        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setUuid(UUID.randomUUID());
        offerEntity.setDescription(createOfferDTO.description());
        offerEntity.setEngine(createOfferDTO.engine());
        offerEntity.setMileage(createOfferDTO.mileage());
        offerEntity.setPrice(createOfferDTO.price());
        offerEntity.setYear(createOfferDTO.year());
        offerEntity.setTransmission(createOfferDTO.transmission());
        offerEntity.setImageUrl(createOfferDTO.imageUrl());


        return offerEntity;
    }
}
