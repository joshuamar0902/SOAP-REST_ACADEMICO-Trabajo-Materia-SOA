# Plataforma Unificada de Servicios Académicos (SOA: SOAP + REST)

Por Joshua Neftalí Marín Leynez


Este proyecto implementa la primera fase de una plataforma académica unificada, siguiendo una Arquitectura Orientada a Servicios (SOA). El objetivo es demostrar la **interoperabilidad** entre servicios **RESTful** modernos (Java/Spring Boot) y servicios **SOAP** tradicionales (Python/Spyne), todos utilizando una **Base de Datos MySQL compartida**.

**Analisis diseño y modelado de la BD**
<img width="644" height="508" alt="image" src="https://github.com/user-attachments/assets/02dda84d-d41a-4d67-a716-cd6be82d2e8d" />


Ambos servicios están diseñados para ser totalmente independientes en su ejecución, pero están unificados. Ambos sistemas acceden al mismo esquema de base de datos MySQL, garantizando que no haya duplicidad ni inconsistencia en los datos.REST (Java): Para gestionar Roles, Usuarios y Carreras.SOAP (Python): Para gestionar las Inscripciones.Flujo de Interoperabilidad (SOAP->REST):Inicio: El cliente (Postman o el frontend) llama a la operación CrearInscripcion al Servicio SOAP (Puerto 8000, XML).Proceso: El servicio SOAP (Python) utiliza SQLAlchemy para realizar un INSERT en la tabla inscripciones de MySQL.Validación (Interoperabilidad): El cliente llama al endpoint GET /api/inscripciones/{id} del Servicio REST (Java, Puerto 1010, JSON).Resultado: El servicio REST (Java) usa JPA para consultar la misma tabla inscripciones de MySQL y devuelve los datos recién creados en formato JSON. 


## Pruebas de Endpoints (Reporte POSTMAN)

Las pruebas se realizaron con Postman, validando el CRUD de los servicios REST (Java) y la funcionalidad de creación y consulta del servicio SOAP (Python).

### 1. Servicios RESTful (Java)

#### CARRERAS

<img width="921" height="383" alt="image" src="https://github.com/user-attachments/assets/f6ec54e0-a29e-474e-92dd-4b970e421ac6" />


#### USUARIOS

<img width="921" height="457" alt="image" src="https://github.com/user-attachments/assets/4681cf43-6c93-403c-bb48-b4ea3920da1f" />


#### ROLES

<img width="921" height="427" alt="image" src="https://github.com/user-attachments/assets/ddb256ee-e270-4ab6-9eac-7da476cbefc7" />


### 2. Servicio SOAP (Python)


<img width="921" height="510" alt="image" src="https://github.com/user-attachments/assets/8bf92c1f-2d5c-4c47-88e1-70e417960e49" />


**Prueba de conexión entre el REST Y SOAP**
<img width="921" height="871" alt="image" src="https://github.com/user-attachments/assets/c13b68b3-6dda-4b04-af2e-47bcd06a32fb" />



**Ejemplo de Petición XML (CrearInscripcion):**

```xml
<soapenv:Envelope xmlns:soapenv="[http://schemas.xmlsoap.org/soap/envelope/](http://schemas.xmlsoap.org/soap/envelope/)"
                  xmlns:acad="urn:academico.inscripciones.service">
   <soapenv:Header/>
   <soapenv:Body>
      <acad:CrearInscripcion>
         <acad:id_usuario>1</acad:id_usuario> 
         <acad:id_carrera>1</acad:id_carrera>
      </acad:CrearInscripcion>
   </soapenv:Body>
</soapenv:Envelope>
