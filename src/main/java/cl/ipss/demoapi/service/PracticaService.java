package cl.ipss.demoapi.service;

import cl.ipss.demoapi.model.Practica;
import cl.ipss.demoapi.repository.PracticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PracticaService {

    @Autowired
    private PracticaRepository practicaRepository;

    // ==================== ESTUDIANTE ====================
    
    /**
     * ESTUDIANTE: Crear su propia práctica
     */
    public Practica crearPracticaEstudiante(Practica practica) {
        return practicaRepository.save(practica);
    }

    /**
     * ESTUDIANTE: Leer solo sus prácticas
     */
    @Transactional(readOnly = true)
    public List<Practica> obtenerPorEstudiante(Long idEstudiante) {
        return practicaRepository.findByEstudianteId(idEstudiante);
    }

    /**
     * ESTUDIANTE: Obtener una práctica específica suya
     */
    @Transactional(readOnly = true)
    public Optional<Practica> obtenerPracticaPorId(Long id) {
        return practicaRepository.findById(id);
    }

    // ==================== PROFESOR ====================
    
    /**
     * PROFESOR: Obtener todas las prácticas (Supervisión)
     */
    @Transactional(readOnly = true)
    public List<Practica> obtenerTodas() {
        return practicaRepository.findAll();
    }

    /**
     * PROFESOR: Obtener prácticas que supervisa
     */
    @Transactional(readOnly = true)
    public List<Practica> obtenerPorProfesor(Long idProfesor) {
        return practicaRepository.findByProfesorSupervisorId(idProfesor);
    }

    /**
     * PROFESOR: Actualizar práctica (Corregir datos)
     */
    public Practica actualizarPractica(Long id, Practica nuevosDatos) {
        Practica existente = practicaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Práctica no encontrada con ID: " + id));
        
        existente.setDescripcionActividades(nuevosDatos.getDescripcionActividades());
        existente.setFechaTermino(nuevosDatos.getFechaTermino());
        existente.setNombreEmpresa(nuevosDatos.getNombreEmpresa());
        existente.setDireccionEmpresa(nuevosDatos.getDireccionEmpresa());
        existente.setTelefonoEmpresa(nuevosDatos.getTelefonoEmpresa());
        existente.setNombreJefeDirecto(nuevosDatos.getNombreJefeDirecto());
        
        return practicaRepository.save(existente);
    }

    /**
     * PROFESOR: Eliminar práctica
     */
    public void eliminarPractica(Long id) {
        if (!practicaRepository.existsById(id)) {
            throw new RuntimeException("Práctica no encontrada con ID: " + id);
        }
        practicaRepository.deleteById(id);
    }
}
