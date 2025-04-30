package cloud.CRUD;

import cloud.model.entity.UnitEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UnitCRUD extends ReactiveMongoRepository<UnitEntity, String> {
    Mono<UnitEntity> findByUnitId(String unitId);
    Flux<UnitEntity> findAllBy(Pageable pageable);
    Flux<UnitEntity> findAllByUsersContaining(String email, Pageable pageable);
}
