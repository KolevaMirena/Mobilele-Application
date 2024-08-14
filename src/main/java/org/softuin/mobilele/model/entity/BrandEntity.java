package org.softuin.mobilele.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    @Column(unique = true, nullable = false)
    private  String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brandEntity")
    List<ModelEntity> model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelEntity> getModel() {
        return model;
    }

    public void setModel(List<ModelEntity> model) {
        this.model = model;
    }
}
