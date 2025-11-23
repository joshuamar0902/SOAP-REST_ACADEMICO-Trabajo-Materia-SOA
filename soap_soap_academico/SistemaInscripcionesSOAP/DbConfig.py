from sqlalchemy import create_engine, Column, Integer, DateTime
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from datetime import datetime

# --- CONFIGURACIÓN DE CONEXIÓN (AJUSTA TUS CREDENCIALES) ---
# Usa el mismo nombre de BD que tu proyecto Java REST
DATABASE_URL = "mysql+mysqlconnector://root:Piolin09@localhost:3306/academicobd"
engine = create_engine(DATABASE_URL)
Base = declarative_base()

# Definición del Modelo de Base de Datos para Inscripciones
class InscripcionDB(Base):
    __tablename__ = 'inscripciones'
    
    id_inscripcion = Column(Integer, primary_key=True, autoincrement=True)
    id_usuario = Column(Integer, nullable=False)   # FK a tabla 'usuarios'
    id_carrera = Column(Integer, nullable=False)   # FK a tabla 'carrera'
    fecha_inscripcion = Column(DateTime, default=datetime.now)

# Crea la tabla 'inscripciones' si no existe
Base.metadata.create_all(engine)

# Configuración de Sesión para transacciones
Session = sessionmaker(bind=engine)