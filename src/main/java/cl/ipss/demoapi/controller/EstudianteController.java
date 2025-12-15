package cl.ipss.demoapi.controller;

import cl.ipss.demoapi.model.Estudiante;
import cl.ipss.demoapi.repository.EstudianteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * POST: Crear nuevo estudiante
     */
    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    /**
     * GET: Obtener todos los estudiantes
     */
    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return ResponseEntity.ok(estudiantes);
    }

    /**
     * GET: Obtener estudiante por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * PUT: Actualizar estudiante
     */
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @PathVariable Long id,
            @Valid @RequestBody Estudiante estudianteActualizado) {
        
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        if (optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            estudiante.setNombreCompleto(estudianteActualizado.getNombreCompleto());
            estudiante.setEmail(estudianteActualizado.getEmail());
            estudiante.setCarrera(estudianteActualizado.getCarrera());
            
            Estudiante actualizado = estudianteRepository.save(estudiante);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE: Eliminar estudiante
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
