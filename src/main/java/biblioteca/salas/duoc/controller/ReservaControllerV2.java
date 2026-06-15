package biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.assemblers.ReservaModelAssembler;
import biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/reservas")
public class ReservaControllerV2 {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getAllReservas() {

        List<EntityModel<Reserva>> reservas = reservaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getAllReservas()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Reserva> getReservaById(@PathVariable Integer id) {

        Reserva reserva = reservaService.findById(id);

        return assembler.toModel(reserva);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> createReserva(@RequestBody Reserva reserva) {

        Reserva newReserva = reservaService.save(reserva);

        return ResponseEntity
                .created(
                        linkTo(
                                methodOn(ReservaControllerV2.class)
                                        .getReservaById(newReserva.getId())
                        ).toUri()
                )
                .body(assembler.toModel(newReserva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> updateReserva(
            @PathVariable Integer id,
            @RequestBody Reserva reserva) {

        reserva.setId(id);

        Reserva updatedReserva = reservaService.save(reserva);

        return ResponseEntity
                .ok(assembler.toModel(updatedReserva));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteReserva(@PathVariable Integer id) {

        reservaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}