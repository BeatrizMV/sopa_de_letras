USE sopaletras;
CREATE TABLE Usuario (
idUsuario int AUTO_INCREMENT PRIMARY KEY,
nombre varchar(50) NOT NULL,
email varchar(100) NOT NULL
);

CREATE TABLE Partida (
idPartida int AUTO_INCREMENT PRIMARY KEY,
fecha date NOT NULL,
puntuacion int NOT NULL,
tiempo int NOT NULL,
idUsuario int NOT NULL,
FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

CREATE TABLE Palabra(
palabra varchar(50) PRIMARY KEY
);

CREATE TABLE PartidaPalabras(
idPartida int AUTO_INCREMENT,
palabra varchar(50),
posicionX int NOT NULL,
posicionY int NOT NULL,
FOREIGN KEY (idPartida) REFERENCES Partida(idPartida),
FOREIGN KEY (palabra) REFERENCES Palabra(palabra),
CONSTRAINT partidaPalabra PRIMARY KEY (IdPartida, palabra)
);