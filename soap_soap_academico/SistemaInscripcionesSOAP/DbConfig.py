from sqlalchemy import create_engine, Column, Integer, DateTime, String, BigInteger# << Añadir String
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from datetime import datetime
# --- CONFIGURACIÓN DE CONEXIÓN (AJUSTA TUS CREDENCIALES) ---
# Usa el mismo nombre de BD que tu proyecto Java REST
DATABASE_URL = "mysql+mysqlconnector://root:Piolin09@localhost:3306/academico_final"
engine = create_engine(DATABASE_URL)
Base = declarative_base()

# Definición del Modelo de Base de Datos para Inscripciones
class InscripcionDB(Base):
    __tablename__ = 'inscripciones'
    
    id_inscripcion = Column(Integer, primary_key=True, autoincrement=True)
    id_usuario = Column(Integer, nullable=False)   # FK a tabla 'usuarios'
    id_carrera = Column(Integer, nullable=False)   # FK a tabla 'carrera'
    fecha_inscripcion = Column(DateTime, default=datetime.now)



class AlumnoDB(Base):
    __tablename__ = 'alumnos'
    
    id_usuario = Column(BigInteger, primary_key=True, autoincrement=True)
    id_carrera = Column(BigInteger, nullable=False) 
    
    # >> ¡CORRECCIÓN! Usar String para campos de texto (VARCHAR)
    nombre = Column(String(80), nullable=False)  # Asumo longitud 80 como antes
    apellido = Column(String(20), nullable=False) # Asumo longitud 20
    email = Column(String(100), nullable=False) # Asumo longitud 100
    
    fecha_nacimiento = Column(DateTime, default=datetime.now)

Base.metadata.create_all(engine)
# Configuración de Sesión para transacciones
Session = sessionmaker(bind=engine)