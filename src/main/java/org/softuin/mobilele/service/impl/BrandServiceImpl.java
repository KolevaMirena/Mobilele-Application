package org.softuin.mobilele.service.impl;


import org.softuin.mobilele.model.dto.BrandDTO;
import org.softuin.mobilele.model.dto.ModelDTO;
import org.softuin.mobilele.model.entity.ModelEntity;
import org.softuin.mobilele.repository.ModelRepository;
import org.softuin.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandServiceImpl implements BrandService {



    private  final ModelRepository modelRepository;

    public BrandServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public List<BrandDTO> getAllBrands() {

        //TODO Better implementation

        Map<String, BrandDTO> brands = new TreeMap<>();

        for (ModelEntity modelEntity : modelRepository.findAll()) {

            if(!brands.containsKey(modelEntity.getBrandEntity().getName()){

                brands.put(modelEntity.getBrandEntity().getName(), new BrandDTO(modelEntity.getBrandEntity().getName(), new ArrayList<>()));

            }

            brands.get(modelEntity.getBrandEntity().getName()).models().add(new ModelDTO(modelEntity.getId(), modelEntity.getName()));

        }

        return brands.values().stream().toList();
    }
}
