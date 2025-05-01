package cloud.controller;

import cloud.model.boundary.UnitBoundary;
import cloud.model.boundary.UnitEmployeeBoundary;
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

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
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

    @PutMapping("/{unitId}/users")
    public Mono<Void> assignUnits(
            @PathVariable String unitId,
            @RequestBody @Valid UnitEmployeeBoundary user
            ) {
        return unitService.addUserToUnit(unitId, user.getEmail());
    }

    @GetMapping(path = "/{unitId}/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE )
    public Flux<UnitEmployeeBoundary> getUsersOfUnit(
            @PathVariable String unitId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return unitService.getUsersOfUnit(unitId, page, size);
    }

    @GetMapping(path = "/{email}/units", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UnitBoundary> getUnitsByUserEmail(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return unitService.getUnitsByUserEmail(email, page, size);
    }

    @DeleteMapping("/{unitId}/users")
    public Mono<Void> removeAllUsers(
            @PathVariable String unitId
    ) {
        return unitService.removeAllUsersFromUnit(unitId);
    }
}
