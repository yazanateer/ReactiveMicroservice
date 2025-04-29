package cloud.CRUD;

import cloud.model.entity.UnitEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UnitCRUD extends ReactiveMongoRepository<UnitEntity, String> {

}
