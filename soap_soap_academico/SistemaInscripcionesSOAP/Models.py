from spyne.model.complex import ComplexModel
from spyne.model.primitive import Integer, DateTime

# Estructura de datos para enviar y recibir en el protocolo SOAP
class InscripcionModelSOAP(ComplexModel):
    id_inscripcion = Integer
    id_usuario = Integer
    id_carrera = Integer
    fecha_inscripcion = DateTime(safe=True)