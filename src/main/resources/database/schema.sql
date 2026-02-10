DROP DATABASE IF EXISTS fichajes;
CREATE DATABASE IF NOT EXISTS fichajes;

USE fichajes;

CREATE TABLE jugador(
id_jugador INT PRIMARY KEY AUTO_INCREMENT,
nombre_jugador VARCHAR (128) NOT NULL,
edad INT NOT NULL,
posicion VARCHAR (50) NOT NULL,
valor_mercado FLOAT (20,2) NOT NULL,
equipo_jugador VARCHAR (128) NOT NULL
);

CREATE TABLE equipo(
id_equipo INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre_equipo VARCHAR (128) NOT NULL,
ciudad VARCHAR (50) NOT NULL,
presupuesto FLOAT (20,2) NOT NULL
);

CREATE TABLE traspaso(
id_traspaso INT PRIMARY KEY AUTO_INCREMENT,
nombre_jugador_traspaso VARCHAR (128) NOT NULL,
nombre_equipo_origen VARCHAR (128) NOT NULL,
nombre_equipo_destino VARCHAR (128) NOT NULL,
precio FLOAT (20,2) NOT NULL
);
