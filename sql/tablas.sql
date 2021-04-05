
--TABLAS
CREATE TABLE Cliente (
documento VARCHAR(50) NOT NULL,
tipoDocumento VARCHAR(50) NOT NULL,
nombre VARCHAR(100) NOT NULL,
correo VARCHAR(50) NOT NULL,
telefono VARCHAR(70) NOT NULL,
password VARCHAR(200) NOT NULL
);

CREATE TABLE Conductor(
documento VARCHAR(50) NOT NULL,
tipoDocumento VARCHAR(50) NOT NULL,
nombre VARCHAR(100) NOT NULL,
correo VARCHAR(50) NOT NULL,
telefono VARCHAR(70) NOT NULL,
password VARCHAR(200) NOT NULL,
calificacion INTEGER NOT NULL,
idViajeEnCurso INTEGER
);

CREATE TABLE Vehiculo(
id INTEGER NOT NULL,
nombre VARCHAR(50) NOT NULL,
marca VARCHAR(50) NOT NULL,
tipo INTEGER NOT NULL,
modelo VARCHAR(50) NOT NULL,
placa VARCHAR(50) NOT NULL,
docCliente VARCHAR(50) NOT NULL,
tipoDocCliente VARCHAR(50) NOT NULL,
docConductor VARCHAR(50),
tipoDocConductor VARCHAR(50),
idViajeEnCurso INTEGER
);

CREATE TABLE Viaje(
id INTEGER NOT NULL,
duracion INTEGER,
precio INTEGER NOT NULL,
especificaciones VARCHAR(300),
idRuta INTEGER NOT NULL
);

CREATE TABLE Oferta(
id INTEGER NOT NULL,
Subasta INTEGER NOT NULL,
creador VARCHAR(50) NOT NULL,
idViaje INTEGER NOT NULL
);

CREATE TABLE Ruta(
id INTEGER NOT NULL,
puntoPartida VARCHAR(50) NOT NULL,
puntoLlegada VARCHAR(50) NOT NULL
);

CREATE TABLE ViajeEnCurso(
id INTEGER NOT NULL,
docConductor VARCHAR(50) NOT NULL,
tipoDocConductor VARCHAR(50) NOT NULL,
idViaje INTEGER NOT NULL,
latitudUbicacion INTEGER NOT NULL,
longitudUbicacion INTEGER NOT NULL
);

CREATE TABLE Ubicacion(
latitud VARCHAR(50) NOT NULL,
longitud VARCHAR(50) NOT NULL
);

CREATE TABLE Cliente_Viaje(
docCliente VARCHAR(50) NOT NULL,
tipoDocCliente VARCHAR(50) NOT NULL,
idViaje INTEGER NOT NULL
);

CREATE TABLE Conductor_Oferta(
docConductor VARCHAR(50) NOT NULL,
tipoDocConductor VARCHAR(50) NOT NULL,
idOferta INTEGER NOT NULL
);

CREATE TABLE Conductor_Viaje(
docConductor VARCHAR(50) NOT NULL,
tipoDocConductor VARCHAR(50) NOT NULL,
idViaje INTEGER NOT NULL
);

CREATE TABLE Cliente_Ruta(
docCliente VARCHAR(50) NOT NULL,
tipoDocCliente VARCHAR(50) NOT NULL,
idRuta INTEGER NOT NULL
);

CREATE TABLE Cliente_Oferta(
docCliente VARCHAR(50) NOT NULL,
tipoDocCliente VARCHAR(50) NOT NULL,
idOferta INTEGER NOT NULL
);

--PRIMARIAS

ALTER TABLE Cliente
   ADD CONSTRAINT PK_Cliente PRIMARY KEY (documento, tipoDocumento);

ALTER TABLE Conductor
   ADD CONSTRAINT PK_Conductor PRIMARY KEY (documento, tipoDocumento);

ALTER TABLE Vehiculo
   ADD CONSTRAINT PK_Vehiculo PRIMARY KEY (id);

ALTER TABLE Viaje
   ADD CONSTRAINT PK_Viaje PRIMARY KEY (id);

ALTER TABLE Oferta
   ADD CONSTRAINT PK_Oferta PRIMARY KEY (id);

ALTER TABLE Ruta
   ADD CONSTRAINT PK_Ruta PRIMARY KEY (id);

ALTER TABLE Ubicacion
   ADD CONSTRAINT PK_Ubicacion PRIMARY KEY (latitud, longitud);

ALTER TABLE ViajeEnCurso
   ADD CONSTRAINT PK_ViajeEnCurso PRIMARY KEY (id);

ALTER TABLE Cliente_Viaje
   ADD CONSTRAINT PK_Cliente_Viaje PRIMARY KEY (docCliente, tipoDocCliente, idViaje);

ALTER TABLE Conductor_Oferta
   ADD CONSTRAINT PK_Conductor_Oferta PRIMARY KEY (docConductor,tipoDocConductor, idOferta);

ALTER TABLE Cliente_Ruta
   ADD CONSTRAINT PK_Cliente_Ruta PRIMARY KEY (docCliente, tipoDocCliente, idRuta);

ALTER TABLE Cliente_Oferta
   ADD CONSTRAINT PK_Cliente_Oferta PRIMARY KEY (docCliente, tipoDocCliente, idOferta);

ALTER TABLE Conductor_Viaje
   ADD CONSTRAINT PK_Conductor_Viaje PRIMARY KEY (docConductor, tipoDocConductor, idViaje);

--FORANEAS

ALTER TABLE Conductor
ADD CONSTRAINT fk_viajeencurso
    FOREIGN KEY (idViajeEnCurso)
    REFERENCES ViajeEnCurso (id);

ALTER TABLE Vehiculo
ADD CONSTRAINT fk_cliente
    FOREIGN KEY (docCliente, tipoDocCliente)
    REFERENCES Cliente (documento, tipoDocumento);

ALTER TABLE Vehiculo
ADD CONSTRAINT fk_conductor_vehiculo
    FOREIGN KEY (docConductor, tipoDocConductor)
    REFERENCES Conductor (documento, tipoDocumento);

ALTER TABLE Vehiculo
ADD CONSTRAINT fk_viaje_vehiculo
    FOREIGN KEY (idViajeEnCurso)
    REFERENCES ViajeEnCurso (id);

ALTER TABLE Viaje
ADD CONSTRAINT fk_ruta
    FOREIGN KEY (idRuta)
    REFERENCES Ruta (id);

ALTER TABLE Oferta
ADD CONSTRAINT fk_viaje_oferta
    FOREIGN KEY (idViaje)
    REFERENCES Viaje (id);

ALTER TABLE ViajeEnCurso
ADD CONSTRAINT fk_conductor
    FOREIGN KEY (docConductor, tipoDocConductor)
    REFERENCES Conductor (documento, tipoDocumento);

ALTER TABLE ViajeEnCurso
ADD CONSTRAINT fk_viaje
    FOREIGN KEY (idViaje)
    REFERENCES Viaje (id);

ALTER TABLE Cliente_Viaje
ADD CONSTRAINT fk_cliente_viaje
    FOREIGN KEY (docCliente, tipoDocCliente)
    REFERENCES Cliente (documento, tipoDocumento);

ALTER TABLE Cliente_Viaje
ADD CONSTRAINT fk_viaje_cliente
    FOREIGN KEY (idViaje)
    REFERENCES Viaje (id);

ALTER TABLE Conductor_Oferta
ADD CONSTRAINT fk_conductor_oferta
    FOREIGN KEY (docConductor, tipoDocConductor)
    REFERENCES Conductor (documento, tipoDocumento);

ALTER TABLE Conductor_Oferta
ADD CONSTRAINT fk_oferta_conductor
    FOREIGN KEY (idOferta)
    REFERENCES Oferta (id);

ALTER TABLE Conductor_Viaje
ADD CONSTRAINT fk_conductor_viaje
    FOREIGN KEY (docConductor, tipoDocConductor)
    REFERENCES Conductor (documento, tipoDocumento);

ALTER TABLE Conductor_Viaje
ADD CONSTRAINT fk_viaje_Conductor
    FOREIGN KEY (idViaje)
    REFERENCES Viaje (id);

ALTER TABLE Cliente_Ruta
ADD CONSTRAINT fk_cliente_ruta
    FOREIGN KEY (docCliente, tipoDocCliente)
    REFERENCES Cliente (documento, tipoDocumento);

ALTER TABLE Cliente_Ruta
ADD CONSTRAINT fk_ruta_cliente
    FOREIGN KEY (idRuta)
    REFERENCES Ruta (id);

ALTER TABLE Cliente_Oferta
ADD CONSTRAINT fk_cliente_oferta
    FOREIGN KEY (docCliente, tipoDocCliente)
    REFERENCES Cliente (documento, tipoDocumento);

ALTER TABLE Cliente_Oferta
ADD CONSTRAINT fk_oferta_cliente
    FOREIGN KEY (idOferta)
    REFERENCES Oferta (id);

--CHECKS

ALTER TABLE Cliente ADD CONSTRAINT CorreoCliente
		CHECK (Cliente.correo LIKE '%@%.%');

ALTER TABLE Conductor ADD CONSTRAINT CorreoConductor
		CHECK (Conductor.correo LIKE '%@%.%');

--SECUENCIAS
CREATE SEQUENCE id_vehiculo;
CREATE SEQUENCE id_viaje;
CREATE SEQUENCE id_oferta;
CREATE SEQUENCE id_ruta;
CREATE SEQUENCE id_viajeEnCurso;