package biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.service.ReservaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Operaciones relacionadas con las reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    @Operation(summary = "Obtener todas las reservas", description = "Obtiene una lista de todas las reservas")
    public List<Reserva> getAllReservas() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable Integer id) {
        return reservaService.findById(id);
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        return reservaService.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Integer id) {
        reservaService.deleteById(id);
    }
}