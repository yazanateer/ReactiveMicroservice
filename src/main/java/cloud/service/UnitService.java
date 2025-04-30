package cloud.service;

import cloud.model.boundary.UnitBoundary;
import cloud.model.boundary.UnitEmployeeBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UnitService {
    Mono<UnitBoundary> create(UnitBoundary unitBoundary);
    Mono<UnitBoundary> getUnitById(String UnitId);
    Flux<UnitBoundary> getAllUnits(int page, int size);
    Mono<Void> updateUnit(String unitId, UnitBoundary unitBoundary);
    Mono<Void> deleteAllUnits();
    Mono<Void> addUserToUnit(String unitId, String email);
    Flux<UnitEmployeeBoundary> getUsersOfUnit(String unitId, int page, int size);
    Flux<UnitBoundary> getUnitsByUserEmail(String email, int page, int size);
    Mono<Void> removeAllUsersFromUnit(String unitId);
}
