package org.softuin.mobilele.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuin.mobilele.model.enums.EngineEnum;
import org.softuin.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;


@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{


    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @NotNull
    @ManyToOne
    private ModelEntity model;

    @NotEmpty
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    @NotEmpty
    private String imageUrl;

    @NotNull
    private long mileage;

    @Positive
    private BigDecimal price;

    @Min(1930)
    private int year;


    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
