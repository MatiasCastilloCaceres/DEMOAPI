# Sistema de Gestión de Prácticas Profesionales

API REST desarrollada con **Spring Boot 3.5.8** y **Java 21** para gestionar prácticas profesionales de estudiantes.

## 🚀 Quick Start

### Requisitos
- Java 21+
- Maven 3.9+
- H2 Database (incluida)

### Ejecutar la aplicación

```bash
cd demoapi
./mvnw spring-boot:run
```

La aplicación inicia en `http://localhost:8080`

---

## 📋 Endpoints Principales

### Estudiantes
- `POST /api/estudiantes` - Crear estudiante
- `GET /api/estudiantes` - Listar estudiantes
- `GET /api/estudiantes/{id}` - Obtener por ID
- `PUT /api/estudiantes/{id}` - Actualizar
- `DELETE /api/estudiantes/{id}` - Eliminar

### Profesores
- `POST /api/profesores` - Crear profesor
- `GET /api/profesores` - Listar profesores
- `GET /api/profesores/{id}` - Obtener por ID
- `PUT /api/profesores/{id}` - Actualizar
- `DELETE /api/profesores/{id}` - Eliminar

### Prácticas
- `POST /api/practicas/estudiante` - Crear práctica
- `GET /api/practicas/profesor/todas` - Listar todas (profesor)
- `GET /api/practicas/estudiante/{id}` - Prácticas del estudiante
- `GET /api/practicas/{id}` - Obtener por ID
- `GET /api/practicas/profesor/{id}` - Prácticas supervisadas
- `PUT /api/practicas/{id}` - Actualizar (profesor)
- `DELETE /api/practicas/{id}` - Eliminar (profesor)

---

---

## 📝 Ejemplo Request

```json
POST http://localhost:8080/api/practicas/estudiante
Content-Type: application/json

{
  "fechaInicio": "2025-01-15",
  "fechaTermino": "2025-03-31",
  "descripcionActividades": "Desarrollo de módulo de autenticación OAuth2",
  "nombreEmpresa": "Tech Solutions Inc",
  "direccionEmpresa": "Av. Principal 123, Santiago",
  "telefonoEmpresa": "2-2234567",
  "nombreJefeDirecto": "Ing. Roberto Flores",
  "estudiante": {"id": 1},
  "profesorSupervisor": {"id": 1}
}
```

---

## ✨ Características

✅ CRUD completo para Estudiantes, Profesores y Prácticas  
✅ Control de acceso por rol (Estudiante vs Profesor)  
✅ Validaciones de datos con Bean Validation  
✅ Auditoría automática (@CreatedDate, @LastModifiedDate)  
✅ Transacciones ACID (@Transactional)  
✅ API REST con soporte CORS  

---

## 📚 Estructura del Proyecto

```
src/main/java/cl/ipss/demoapi/
├── model/          (Entidades JPA)
├── repository/     (Spring Data JPA)
├── service/        (Lógica de negocio)
└── controller/     (REST Endpoints)
```

---




# DEMOAPI
