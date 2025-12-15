package cl.ipss.demoapi.controller;

import cl.ipss.demoapi.model.Profesor;
import cl.ipss.demoapi.repository.ProfesorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = "*")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    /**
     * POST: Crear nuevo profesor
     */
    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorRepository.save(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProfesor);
    }

    /**
     * GET: Obtener todos los profesores
     */
    @GetMapping
    public ResponseEntity<List<Profesor>> obtenerProfesores() {
        List<Profesor> profesores = profesorRepository.findAll();
        return ResponseEntity.ok(profesores);
    }

    /**
     * GET: Obtener profesor por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerProfesor(@PathVariable Long id) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        return profesor.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * PUT: Actualizar profesor
     */
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(
            @PathVariable Long id,
            @Valid @RequestBody Profesor profesorActualizado) {
        
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            profesor.setNombreCompleto(profesorActualizado.getNombreCompleto());
            profesor.setEmail(profesorActualizado.getEmail());
            profesor.setEspecialidad(profesorActualizado.getEspecialidad());
            
            Profesor actualizado = profesorRepository.save(profesor);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE: Eliminar profesor
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Long id) {
        if (profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
