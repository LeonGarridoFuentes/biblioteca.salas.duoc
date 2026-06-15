package biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.service.TipoSalaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-salas")
@Tag(name = "Tipo de salas", description = "Operaciones relacionadas con los tipos de salas")
public class TipoSalaController {
    @Autowired
    private TipoSalaService tipoSalaService;

    @GetMapping
    @Operation(summary = "Obtener todas los tipos de sala", description = "Obtiene una lista de todas los tipos de salas")
    public List<TipoSala> getAllTipoSalas() {
        return tipoSalaService.findAll();
    }

    @GetMapping("/{id}")
    public TipoSala getTipoSalaById(@PathVariable Integer id) {
        return tipoSalaService.findById(id);
    }

    @PostMapping
    public TipoSala createTipoSala(@RequestBody TipoSala tipoSala) {
        return tipoSalaService.save(tipoSala);
    }

    @PutMapping("/{id}")
    public TipoSala updateTipoSala(@PathVariable Integer id, @RequestBody TipoSala tipoSala) {
        tipoSala.setIdTipo(id);
        return tipoSalaService.save(tipoSala);
    }

    @DeleteMapping("/{id}")
    public void deleteTipoSala(@PathVariable Integer id) {
        tipoSalaService.deleteById(id);
    }
}
