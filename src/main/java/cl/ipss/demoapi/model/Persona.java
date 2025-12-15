package cl.ipss.demoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCompleto;
    
    @Email(message = "El email debe ser válido")
    private String email;
}
