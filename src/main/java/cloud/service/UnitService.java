package cloud.service;

import cloud.model.boundary.UnitBoundary;
import cloud.model.entity.UnitEntity;
import reactor.core.publisher.Mono;

public interface UnitService {
    Mono<UnitBoundary> create(UnitBoundary unitBoundary);
}
