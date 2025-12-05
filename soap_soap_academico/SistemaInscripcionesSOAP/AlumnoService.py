# AlumnoService.py

from spyne.decorator import rpc
from spyne.service import ServiceBase
# Importa los tipos primitivos necesarios
from spyne.model.primitive import Integer, Boolean, Unicode, DateTime, Long
from spyne.model.fault import Fault
from datetime import datetime
# Importa tu modelo de datos SOAP y tus clases de base de datos
from Models import AlumnoModelSOAP
from DbConfig import Session, AlumnoDB  # Asumimos que AlumnoDB es tu ORM/Tabla ALUMNOS

class AlumnoService(ServiceBase):
    """
    Implementa las operaciones SOAP para la gestión de Alumnos (Entidad Matrícula).
    """
    __namespace__ = 'urn:academico.alumnos.service'

    # Operación 1: REGISTRAR/CREAR ALUMNO (POST)
    # Debe recibir todos los campos obligatorios del AlumnoModelSOAP.
    # Recibe 6 argumentos de datos: id_usuario (PK, si no es autoincrementable), nombre, apellido, email, fecha_nacimiento, id_carrera (FK)
    @rpc(Long, Unicode, Unicode, Unicode, DateTime,Long, _returns=AlumnoModelSOAP)
    def RegistrarAlumno(ctx, id_usuario, nombre, apellido, email, fecha_nacimiento, id_carrera):
        session = Session()
        try:
            nuevo_alumno = AlumnoDB(
                id_usuario=id_usuario,
                nombre=nombre,
                apellido=apellido,
                email=email,
                fecha_nacimiento=fecha_nacimiento,
                id_carrera=id_carrera
            )
            session.add(nuevo_alumno)
            session.commit()
            
            # Retorna el objeto creado
            return AlumnoModelSOAP(
                id_usuario=nuevo_alumno.id_usuario,
                nombre=nuevo_alumno.nombre,
                apellido=nuevo_alumno.apellido,
                email=nuevo_alumno.email,
                fecha_nacimiento=nuevo_alumno.fecha_nacimiento,
                id_carrera=nuevo_alumno.id_carrera
            )
        except Exception as e:
            session.rollback()
            raise Fault(faultcode='Server', faultstring=f"Error al registrar alumno: {e}")
        finally:
            session.close()

    # Operación 2: OBTENER ALUMNO POR ID (Consultar alumno por matrícula)
    @rpc(Long, _returns=AlumnoModelSOAP.customize(min_occurs=0))
    def ObtenerAlumnoPorId(ctx, id_usuario):
        session = Session()
        try:
            alumno = session.query(AlumnoDB).get(id_usuario)

            if alumno:
                alumno_soap = AlumnoModelSOAP(
                    id_usuario=alumno.id_usuario,
                    nombre=alumno.nombre,
                    apellido=alumno.apellido,
                    email=alumno.email,
                    fecha_nacimiento=alumno.fecha_nacimiento,
                    id_carrera=alumno.id_carrera
                )

                return alumno_soap
            else:
                return None 

        except Exception as e:
            raise Fault(faultcode='Server', faultstring=f"Error al consultar alumno: {e}")
        finally:

            session.close()

    # Recibe el ID y todos los campos nuevos a actualizar.
    @rpc(Long, Unicode, Unicode, Unicode, DateTime,Long, _returns=AlumnoModelSOAP)
    def EditarAlumno(ctx, id_usuario, nuevo_nombre, nuevo_apellido, nuevo_email, nueva_fecha_nacimiento, nuevo_id_carrera):
        session = Session()
        try:
            alumno = session.query(AlumnoDB).get(id_usuario)
            if not alumno:
                raise Fault(faultcode='Client', faultstring=f"Alumno con ID {id_usuario} no encontrado para actualizar.")
            
            # Aplica los cambios
            alumno.nombre = nuevo_nombre
            alumno.apellido = nuevo_apellido
            alumno.email = nuevo_email
            alumno.fecha_nacimiento = nueva_fecha_nacimiento
            alumno.id_carrera = nuevo_id_carrera
            
            session.commit()
            
            # Retorna el objeto actualizado
            return AlumnoModelSOAP(
                id_usuario=alumno.id_usuario,
                nombre=alumno.nombre,
                apellido=alumno.apellido,
                email=alumno.email,
                fecha_nacimiento=alumno.fecha_nacimiento,
                id_carrera=alumno.id_carrera
            )
        except Exception as e:
            session.rollback()
            raise Fault(faultcode='Server', faultstring=f"Error al editar alumno: {e}")
        finally:
            session.close()


    # Operación 4: ELIMINAR ALUMNO (DELETE)
    @rpc(Long, _returns=Boolean)
    def EliminarAlumno(ctx, id_usuario):
        session = Session()
        try:
            alumno = session.query(AlumnoDB).get(id_usuario)
            if not alumno:
                return False # No encontrado, pero no es un error de servidor
            
            session.delete(alumno)
            session.commit()
            return True # Éxito
        except Exception as e:
            session.rollback()
            raise Fault(faultcode='Server', faultstring=f"Error al eliminar alumno: {e}")
        finally:
            session.close()