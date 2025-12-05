from spyne.model.complex import ComplexModel
from spyne.model.primitive import Integer, DateTime, Unicode, Long

# Estructura de datos para enviar y recibir en el protocolo SOAP
class InscripcionModelSOAP(ComplexModel):
    id_inscripcion = Integer
    id_usuario = Integer
    id_carrera = Integer
    fecha_inscripcion = DateTime(safe=True)

class AlumnoModelSOAP(ComplexModel):
    id_usuario = Long
    id_carrera = Long # <- Añadido para reflejar el @ManyToOne a Carrera
    nombre = Unicode
    apellido = Unicode
    email = Unicode
    fecha_nacimiento = DateTime(safe=True)
    
