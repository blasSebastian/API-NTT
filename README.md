### **README - API de Gestión de Usuarios**

---

### **Descripción**
Esta aplicación es una API REST para la gestión de usuarios y sus teléfonos. Permite realizar operaciones como la creación, actualización, consulta y eliminación de usuarios, así como la validación de contraseñas y autenticación mediante JWT.

---

### **Características**
- **Gestión de Usuarios:**
  - Crear, actualizar, consultar y eliminar usuarios.
  - Asociar teléfonos a los usuarios.
- **Autenticación:**
  - Autenticación mediante JWT (JSON Web Tokens).
  - Validación de tokens para proteger los endpoints.
- **Validación de Contraseñas:**
  - Reglas configurables para contraseñas (longitud mínima/máxima, caracteres especiales, etc.).
- **Documentación:**
  - Documentación interactiva generada con Swagger/OpenAPI.

---

### **Requisitos**
- **Java:** 17 o superior.
- **Maven:** 3.8 o superior.
- **Base de datos:** H2 (base de datos en memoria para desarrollo).
- **Spring Boot:** 3.4.5

---

### **Configuración**
#### **1. Configuración de JWT**
En el archivo `application.properties`, configura el secreto y el tiempo de expiración del token JWT:
```properties
jwt.secret=TuClaveSecretaSuperSeguraDe32Caracteres!
jwt.expiration=86400000
```

#### **2. Configuración de la base de datos**
La aplicación utiliza H2 como base de datos en memoria para desarrollo. Los datos iniciales se cargan desde el archivo data.sql.

**Archivo `application.properties`:**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql
spring.sql.init.data-locations=classpath:data.sql
```

---

### **Ejecución**
1. Clona el repositorio:
   ```bash
   git clone https://github.com/blasSebastian/API-NTT.git
   cd API-NTT
   ```

2. Compila y ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

3. Accede a la documentación de la API en Swagger:
   - URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### **Endpoints Principales**
#### **Autenticación**
- **POST** `/api/auth/login`: Genera un token JWT para un usuario válido.
    **Request Body:**
    ```json
    {
        "correo": "juanperez@example.com",
        "contraseña": "password123",
    }
    ```

#### **Usuarios**
- **GET** `/api/usuarios/{id}`: Obtiene un usuario por su ID.
- **POST** `/api/usuarios`: Crea un nuevo usuario.
    **Request Body:**
    ```json
    {
        "nombre": "Juan Pérez",
        "correo": "juanperez@example.com",
        "contraseña": "password123",
        "telefonos": [
            {
            "numero": "123456789",
            "codigo_ciudad": "91",
            "codigo_pais": "34"
            }
        ]
    }
    ```
- **PUT** `/api/usuarios/{id}`: Actualiza un usuario existente.
    **Request Body:**
    ```json
    {
        "nombre": "Juan Pérez",
        "correo": "juan.perez@example.com",
        "contraseña": "12345Lk",
        "activo": false,
        "telefonos": [
            {
            "numero": 77777777,
            "codigo_ciudad": 77,
            "codigo_pais": 7
            }
        ]
    }
    ```
- **PATCH** `/api/usuarios/{id}`: Actualiza parcialmente un usuario.
    **Request Body:**
    ```json
    {
        "correo": "carlosgarcia@example.com",
        "telefonos": [
            {
            "numero": "555555555",
            "codigo_ciudad": "95",
            "codigo_pais": "34"
            }
        ]
    }
  ```
- **DELETE** `/api/usuarios/{id}`: Elimina un usuario.

#### **Contraseñas**
- **GET** `/api/password`: Obtiene las reglas de validación de contraseñas.
- **PUT** `/api/password`: Actualiza las reglas de validación de contraseñas.
    **Request Body:**
    ```json
    {
        "longitud_minima": 1,
        "longitud_maxima": 20,
        "requiere_mayuscula": true,
        "requiere_minuscula": true,
        "requiere_digito": false,
        "caracter_especial": false,
        "caracteres_permitidos": "!@#$%^&*()_+"
    }
    ```

---

### **Estructura del Proyecto**
```plaintext
src/main/java/cl/ntt/usercreation
├── config          # Configuración de Swagger y JWT
├── controller      # Controladores REST
├── entity          # Entidades JPA
├── exception       # Manejo de excepciones globales
├── repository      # Repositorios JPA
├── service         # Lógica de negocio
├── util            # Utilidades (como JwtUtil)
├── validation      # Validadores personalizados
```

---

### **Datos Iniciales**
Los datos iniciales se cargan desde el archivo *data.sql*. Ejemplo de datos precargados:

#### **Usuarios:**
| ID                                    | Nombre       | Email                  | Contraseña       |
|---------------------------------------|--------------|------------------------|------------------|
| 550e8400-e29b-41d4-a716-446655440000 | Juan Pérez   | juanperez@example.com  | password123      |
| 550e8400-e29b-41d4-a716-446655440001 | María López  | marialopez@example.com | securepass456    |
| 550e8400-e29b-41d4-a716-446655440002 | Carlos García| carlosgarcia@example.com| mypassword789    |

#### **Teléfonos:**
| ID                                    | ID Usuario                           | Código País | Código Ciudad | Número     |
|---------------------------------------|---------------------------------------|-------------|---------------|------------|
| 550e8400-e29b-41d4-a716-446655440003 | 550e8400-e29b-41d4-a716-446655440000 | 34          | 91            | 123456789  |
| 550e8400-e29b-41d4-a716-446655440004 | 550e8400-e29b-41d4-a716-446655440001 | 34          | 93            | 987654321  |
| 550e8400-e29b-41d4-a716-446655440005 | 550e8400-e29b-41d4-a716-446655440002 | 34          | 95            | 555555555  |

### **Diagramas**
Para ver los diagramas se debe abrir el archivo UserCreationApi.drawio en el siguiente link https://app.diagrams.net/.

### **POSTMAN**
Para la ejecución de esta API se deja un collection -> API-NTT.postman_collection.json, que puede ser importado en el programa POSTMAN.

---