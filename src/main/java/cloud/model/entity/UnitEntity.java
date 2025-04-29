package cloud.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;


@Document(collection = "units")
public class UnitEntity {

    @Id
    private String unitId;

    private String name;
    private String creationDate;
    private String description;

    private Set<String> users = new HashSet<>();

    public UnitEntity() {

    }

    public UnitEntity(String unitId, String name, String creationDate, String description) {
        this.unitId = unitId;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
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

    public void setUsers(Set<String> users) {
        this.users = users;
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

    public Set<String> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "unitEntity{" +
                "unitId='" + unitId + '\'' +
                ", name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
