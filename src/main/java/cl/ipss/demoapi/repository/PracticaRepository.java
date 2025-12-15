package cl.ipss.demoapi.repository;

import cl.ipss.demoapi.model.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {
    List<Practica> findByEstudianteId(Long estudianteId);
    List<Practica> findByProfesorSupervisorId(Long profesorId);
}
