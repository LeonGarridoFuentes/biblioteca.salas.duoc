package biblioteca.salas.duoc.controller;
import biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.service.EstudianteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones relacionadas con los estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    @Operation(summary = "Obtener todas los estudiantes", description = "Obtiene una lista de todas las estudiantes")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public Estudiante getEstudianteById(@PathVariable Integer id) {
        return estudianteService.findById(id);
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable Integer id, @RequestBody Estudiante estudiante) {
        estudiante.setId(id);
        return estudianteService.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Integer id) {
        estudianteService.deleteById(id);
    }
}
