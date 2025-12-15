package cl.ipss.demoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "estudiantes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Estudiante extends Persona {
    
    @NotBlank(message = "La carrera es obligatoria")
    private String carrera;
}
