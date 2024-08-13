package org.softuin.mobilele.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    @Column(unique = true, nullable = false)
    private  String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
