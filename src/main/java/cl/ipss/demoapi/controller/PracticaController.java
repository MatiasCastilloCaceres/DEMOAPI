package cl.ipss.demoapi.controller;

import cl.ipss.demoapi.model.Practica;
import cl.ipss.demoapi.service.PracticaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/practicas")
@CrossOrigin(origins = "*")
public class PracticaController {

    @Autowired
    private PracticaService practicaService;

    // ==================== ESTUDIANTE ====================
    
    /**
     * POST: Crear nueva práctica (Estudiante)
     */
    @PostMapping("/estudiante")
    public ResponseEntity<Practica> crearPracticaEstudiante(@Valid @RequestBody Practica practica) {
        Practica nuevaPractica = practicaService.crearPracticaEstudiante(practica);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPractica);
    }

    /**
     * GET: Obtener todas las prácticas de un estudiante
     */
    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<Practica>> obtenerPracticasEstudiante(@PathVariable Long idEstudiante) {
        List<Practica> practicas = practicaService.obtenerPorEstudiante(idEstudiante);
        return ResponseEntity.ok(practicas);
    }

    /**
     * GET: Obtener una práctica específica
     */
    @GetMapping("/{id}")
    public ResponseEntity<Practica> obtenerPractica(@PathVariable Long id) {
        Optional<Practica> practica = practicaService.obtenerPracticaPorId(id);
        return practica.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ==================== PROFESOR ====================
    
    /**
     * GET: Obtener todas las prácticas (Profesor)
     */
    @GetMapping("/profesor/todas")
    public ResponseEntity<List<Practica>> obtenerTodasPracticas() {
        List<Practica> practicas = practicaService.obtenerTodas();
        return ResponseEntity.ok(practicas);
    }

    /**
     * GET: Obtener prácticas que supervisa un profesor
     */
    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<List<Practica>> obtenerPracticasProfesor(@PathVariable Long idProfesor) {
        List<Practica> practicas = practicaService.obtenerPorProfesor(idProfesor);
        return ResponseEntity.ok(practicas);
    }

    /**
     * PUT: Actualizar práctica (Profesor)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Practica> actualizarPractica(
            @PathVariable Long id,
            @Valid @RequestBody Practica practicaActualizada) {
        try {
            Practica practica = practicaService.actualizarPractica(id, practicaActualizada);
            return ResponseEntity.ok(practica);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE: Eliminar práctica (Profesor)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPractica(@PathVariable Long id) {
        try {
            practicaService.eliminarPractica(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
