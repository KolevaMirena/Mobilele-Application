package org.softuin.mobilele.service.impl;


import org.softuin.mobilele.model.dto.BrandDTO;
import org.softuin.mobilele.model.dto.ModelDTO;
import org.softuin.mobilele.repository.BrandRepository;
import org.softuin.mobilele.repository.ModelRepository;
import org.softuin.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {



    private final BrandRepository brandRepository;
    private  final ModelRepository modelRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }


    @Override
    public List<BrandDTO> getAllBrands() {

        return brandRepository.findAll().stream()
                .map(brand -> new BrandDTO(
                        brand.getName(),
                        brand.getModel().stream()
                                .map(model -> new ModelDTO(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDTO::name)).collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(BrandDTO::name)).collect(Collectors.toList());
    }
}
