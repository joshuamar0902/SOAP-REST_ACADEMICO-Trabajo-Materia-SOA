from spyne.decorator import rpc
from spyne.service import ServiceBase
from spyne.model.primitive import Integer
from spyne.model.fault import Fault
from datetime import datetime
from Models import InscripcionModelSOAP
from DbConfig import Session, InscripcionDB

class InscripcionesService(ServiceBase):
    """
    Implementa las operaciones SOAP para la gestión de Inscripciones.
    """
    
    # Operación 1: CREAR INSCRIPCIÓN (Ligada a una transacción de BD)
    @rpc(Integer, Integer, _returns=InscripcionModelSOAP)
    def CrearInscripcion(ctx, id_usuario, id_carrera):
        session = Session()
        try:
            # Crea la instancia para la BD
            nueva_inscripcion = InscripcionDB(
                id_usuario=id_usuario,
                id_carrera=id_carrera,
                fecha_inscripcion=datetime.now()
            )
            session.add(nueva_inscripcion)
            session.commit()
            
            # Retorna el objeto en el formato SOAP
            return InscripcionModelSOAP(
                id_inscripcion=nueva_inscripcion.id_inscripcion,
                id_usuario=nueva_inscripcion.id_usuario,
                id_carrera=nueva_inscripcion.id_carrera,
                fecha_inscripcion=nueva_inscripcion.fecha_inscripcion
            )
        except Exception as e:
            session.rollback()
            raise Fault(faultcode='Server', faultstring=f"Error en BD al crear inscripción: {e}")
        finally:
            session.close()

    # Operación 2: OBTENER INSCRIPCIÓN
    @rpc(Integer, _returns=InscripcionModelSOAP)
    def ObtenerInscripcionPorId(ctx, id_inscripcion):
        session = Session()
        inscripcion = session.query(InscripcionDB).get(id_inscripcion)
        session.close()

        if inscripcion:
            return InscripcionModelSOAP(
                id_inscripcion=inscripcion.id_inscripcion,
                id_usuario=inscripcion.id_usuario,
                id_carrera=inscripcion.id_carrera,
                fecha_inscripcion=inscripcion.fecha_inscripcion
            )
        else:
            raise Fault(faultcode='Client', faultstring=f"Inscripción con ID {id_inscripcion} no encontrada.")


# 3. ACTUALIZAR INSCRIPCIÓN (UPDATE)
    @rpc(Integer, Integer, Integer, _returns=InscripcionModelSOAP)
    def ActualizarInscripcion(ctx, id_inscripcion, nuevo_id_usuario, nuevo_id_carrera):
        session = Session()
        try:
            inscripcion = session.query(InscripcionDB).get(id_inscripcion)
            if not inscripcion:
                raise Fault(faultcode='Client', faultstring=f"Inscripción con ID {id_inscripcion} no encontrada para actualizar.")
            
            # Aplica los cambios
            inscripcion.id_usuario = nuevo_id_usuario
            inscripcion.id_carrera = nuevo_id_carrera
            
            session.commit()
            
            # Retorna el objeto actualizado
            return InscripcionModelSOAP(
                id_inscripcion=inscripcion.id_inscripcion,
                id_usuario=inscripcion.id_usuario,
                id_carrera=inscripcion.id_carrera,
                fecha_inscripcion=inscripcion.fecha_inscripcion
            )
        except Exception as e:
            session.rollback()
            raise Fault(faultcode='Server', faultstring=f"Error al actualizar inscripción: {e}")
        finally:
            session.close()

    # 4. ELIMINAR INSCRIPCIÓN (DELETE)
    @rpc(Integer, _returns=Boolean)
    def EliminarInscripcion(ctx, id_inscripcion):
        session = Session()
        try:
            inscripcion = session.query(InscripcionDB).get(id_inscripcion)
            if not inscripcion:
                # Retornamos True o False en lugar de fallar, aunque podrías lanzar una Fault
                return False 
            
            session.delete(inscripcion)
            session.commit()
            return True # Éxito
        except Exception as e:
            session.rollback()
            raise Fault(faultcode='Server', faultstring=f"Error al eliminar inscripción: {e}")
        finally:
            session.close()