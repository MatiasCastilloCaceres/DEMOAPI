package cl.ipss.demoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "practicas")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Practica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    
    @NotNull(message = "La fecha de término es obligatoria")
    private LocalDate fechaTermino;

    @Size(min = 10, message = "La descripción debe ser detallada (mínimo 10 caracteres)")
    private String descripcionActividades;

    // Datos de Empresa
    private String nombreEmpresa;
    private String direccionEmpresa;
    private String telefonoEmpresa;
    private String nombreJefeDirecto;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesorSupervisor;
    
    // Campos de Auditoría
    @CreatedDate
    private LocalDateTime fechaRegistro;
    
    @LastModifiedDate
    private LocalDateTime ultimaActualizacion;
}
