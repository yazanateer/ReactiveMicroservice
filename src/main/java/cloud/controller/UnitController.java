package cloud.controller;

import cloud.model.boundary.UnitBoundary;
import cloud.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UnitBoundary> createUnit(@Valid @RequestBody UnitBoundary unitBoundary){
        return unitService.create(unitBoundary);
    }

    @GetMapping("/{unitId}")
    public Mono<UnitBoundary> getUnitById(@PathVariable String unitId) {
        return unitService.getUnitById(unitId);
    }

    @GetMapping
    public Flux<UnitBoundary> getAllUnits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return unitService.getAllUnits(page, size);
    }

    @PutMapping("/{unitId}")
    public Mono<Void> updateUnit(
            @PathVariable String unitId,
            @RequestBody UnitBoundary unitBoundary
    ) {
        return unitService.updateUnit(unitId, unitBoundary);
    }

    @DeleteMapping
    public Mono<Void> deleteAllUnits() {
        return unitService.deleteAllUnits();
    }
}
