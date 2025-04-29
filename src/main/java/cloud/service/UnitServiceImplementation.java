package cloud.service;

import cloud.CRUD.UnitCRUD;
import cloud.model.boundary.UnitBoundary;
import org.springframework.stereotype.Service;
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
}
