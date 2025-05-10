package cloud.model.boundary;

import cloud.model.entity.UnitEntity;
import jakarta.validation.constraints.NotBlank;

public class UnitBoundary {

    private String unitId;

    @NotBlank(message = "you should enter the name")
    private String name;

    private String creationDate;

    @NotBlank(message = "you should enter the description")
    private String description;



    public UnitBoundary() {

    }

    public UnitBoundary(UnitEntity entity) {
        this.unitId = entity.getUnitId();
        this.name = entity.getName();
        this.creationDate = entity.getCreationDate();
        this.description = entity.getDescription();
    }

    public UnitEntity toEntity() {
        UnitEntity entity = new UnitEntity();
        entity.setUnitId(this.unitId);
        entity.setName(this.name);
        entity.setCreationDate(this.creationDate);
        entity.setDescription(this.description);
        return entity;
    }



    public String getUnitId() {
        return unitId;
    }

    public String getName() {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "UnitBoundary {" +
                 "unitId='" + unitId + '\'' +
                ", name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
