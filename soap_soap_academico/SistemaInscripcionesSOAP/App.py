from wsgiref.simple_server import make_server
from spyne.protocol.soap import Soap11
from spyne.application import Application
from spyne.server.wsgi import WsgiApplication
import logging

from InscripcionesService import InscripcionesService
from AlumnoService import AlumnoService


# Configuración de registro

logging.basicConfig(level=logging.INFO)

# 1. Crear la aplicación SpyNE
app_soap = Application(
    [InscripcionesService,AlumnoService], 
    tns='urn:academico.alumnos.service',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

# 2. Envolver en una aplicación WSGI 
wsgi_app = WsgiApplication(app_soap)

# 3. Iniciar el servidor
if __name__ == '__main__':
    logging.info("Escuchando en http://127.0.0.1:8000")
    logging.info("WSDL disponible en http://127.0.0.1:8000/?wsdl")
    
    # Usamos el puerto 8000 para evitar conflictos con tu API REST de Java
    server = make_server('0.0.0.0', 8000, wsgi_app) 
    server.serve_forever()