package cl.ipss.demoapi.config;

import cl.ipss.demoapi.model.Estudiante;
import cl.ipss.demoapi.model.Profesor;
import cl.ipss.demoapi.model.Practica;
import cl.ipss.demoapi.repository.EstudianteRepository;
import cl.ipss.demoapi.repository.ProfesorRepository;
import cl.ipss.demoapi.repository.PracticaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(
            EstudianteRepository estudianteRepository,
            ProfesorRepository profesorRepository,
            PracticaRepository practicaRepository) {
        
        return args -> {
            // Crear estudiantes
            Estudiante est1 = new Estudiante();
            est1.setNombreCompleto("Juan Pérez García");
            est1.setEmail("juan.perez@ipss.cl");
            est1.setCarrera("Informática");
            est1 = estudianteRepository.save(est1);

            Estudiante est2 = new Estudiante();
            est2.setNombreCompleto("María López Rodríguez");
            est2.setEmail("maria.lopez@ipss.cl");
            est2.setCarrera("Administración");
            est2 = estudianteRepository.save(est2);

            // Crear profesores
            Profesor prof1 = new Profesor();
            prof1.setNombreCompleto("Dr. Carlos Martínez");
            prof1.setEmail("carlos.martinez@ipss.cl");
            prof1.setEspecialidad("Tecnología");
            prof1 = profesorRepository.save(prof1);

            Profesor prof2 = new Profesor();
            prof2.setNombreCompleto("Dra. Sandra González");
            prof2.setEmail("sandra.gonzalez@ipss.cl");
            prof2.setEspecialidad("Negocios");
            prof2 = profesorRepository.save(prof2);

            // Crear prácticas
            Practica practica1 = new Practica();
            practica1.setEstudiante(est1);
            practica1.setProfesorSupervisor(prof1);
            practica1.setFechaInicio(LocalDate.of(2024, 9, 1));
            practica1.setFechaTermino(LocalDate.of(2024, 11, 30));
            practica1.setDescripcionActividades("Desarrollo de aplicaciones web con Java y Spring Boot");
            practica1.setNombreEmpresa("TechCorp Solutions");
            practica1.setDireccionEmpresa("Av. Principal 123, Santiago");
            practica1.setTelefonoEmpresa("2-2234567");
            practica1.setNombreJefeDirecto("Ing. Roberto Flores");
            practicaRepository.save(practica1);

            Practica practica2 = new Practica();
            practica2.setEstudiante(est2);
            practica2.setProfesorSupervisor(prof2);
            practica2.setFechaInicio(LocalDate.of(2024, 8, 15));
            practica2.setFechaTermino(LocalDate.of(2024, 11, 15));
            practica2.setDescripcionActividades("Análisis de mercado y gestión de proyectos empresariales");
            practica2.setNombreEmpresa("Business Consulting");
            practica2.setDireccionEmpresa("Calle Secundaria 456, Valparaíso");
            practica2.setTelefonoEmpresa("3-2345678");
            practica2.setNombreJefeDirecto("Lic. Patricia Sánchez");
            practicaRepository.save(practica2);

            System.out.println("✓ Datos iniciales cargados exitosamente");
        };
    }
}
