package biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.service.SalaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@Tag(name = "Salas", description = "Operaciones relacionadas con las salas")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @GetMapping
    @Operation(summary = "Obtener todas las salas", description = "Obtiene una lista de todas las salas")
    public List<Sala> getAllSalas() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    public Sala getSalaById(@PathVariable Integer id) {
        return salaService.findById(id);
    }

    @PostMapping
    public Sala createSala(@RequestBody Sala sala) {
        return salaService.save(sala);
    }

    @PutMapping("/{id}")
    public Sala updateSala(@PathVariable Integer id, @RequestBody Sala sala) {
        sala.setCodigo(id);
        return salaService.save(sala);
    }

    @DeleteMapping("/{id}")
    public void deleteSala(@PathVariable Integer id) {
        salaService.deleteById(id);
    }
}
