package org.softuin.mobilele.model.entity;


import jakarta.persistence.*;
import org.softuin.mobilele.model.enums.ModelCategoryEnum;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {


    private String name;

    @Enumerated(EnumType.STRING)
    private ModelCategoryEnum modelCategory;


    @ManyToOne
    private BrandEntity brandEntity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategoryEnum getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(ModelCategoryEnum modelCategory) {
        this.modelCategory = modelCategory;
    }

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
    }
}
