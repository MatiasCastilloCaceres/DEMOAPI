package cl.ipss.demoapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "profesores")
@Data
@EqualsAndHashCode(callSuper = true)
public class Profesor extends Persona {
    
    private String especialidad;
}
