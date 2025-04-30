package cloud.service;

import cloud.CRUD.UnitCRUD;
import cloud.model.boundary.UnitBoundary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UnitServiceImplementation implements UnitService{

    private final UnitCRUD unitCRUD;

    public UnitServiceImplementation(UnitCRUD unitCRUD) {
        this.unitCRUD = unitCRUD;
    }

    @Override
    public Mono<UnitBoundary> create(UnitBoundary unitBoundary) {
        return Mono.just(unitBoundary)
                .flatMap(boundary-> {
                    if(boundary.getName() == null || boundary.getDescription() == null)
                        return Mono.error(new IllegalArgumentException("Name and Description are required"));
                    boundary.setUnitId(UUID.randomUUID().toString());
                    boundary.setCreationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    return Mono.just(boundary);
                })
                .map(UnitBoundary::toEntity)
                .flatMap(unitCRUD::save)
                .map(UnitBoundary::new)
                .log();
    }

    @Override
    public Mono<UnitBoundary> getUnitById(String unitId) {
        return unitCRUD.findByUnitId(unitId)
                .map(UnitBoundary::new);
    }

    @Override
    public Flux<UnitBoundary> getAllUnits(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return unitCRUD.findAllBy(pageable)
                .map(UnitBoundary::new);

    }

    @Override
    public Mono<Void> updateUnit(String unitId, UnitBoundary unitBoundary) {
        return unitCRUD.findById(unitId)
                .switchIfEmpty(Mono.error(new RuntimeException("Unit not found")))
                .flatMap(unit -> {
                    unit.setName(unitBoundary.getName());
                    unit.setDescription(unitBoundary.getDescription());
                    return unitCRUD.save(unit);
                }).then();
    }

    @Override
    public Mono<Void> deleteAllUnits(){
        return unitCRUD.deleteAll();
    }
}
