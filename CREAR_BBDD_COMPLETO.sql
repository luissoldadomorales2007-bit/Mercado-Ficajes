-- ================================================
-- SCRIPT COMPLETO PARA CREAR BASE DE DATOS
-- Mercado de Fichajes - Grupo 2
-- ================================================

-- PASO 1: Crear la base de datos
DROP DATABASE IF EXISTS fichajes;
CREATE DATABASE IF NOT EXISTS fichajes;

-- PASO 2: Usar la base de datos
USE fichajes;

-- PASO 3: Crear tabla de jugadores
CREATE TABLE jugador(
    id_jugador INT PRIMARY KEY AUTO_INCREMENT,
    nombre_jugador VARCHAR(128) NOT NULL,
    edad INT NOT NULL,
    posicion VARCHAR(50) NOT NULL,
    valor_mercado FLOAT(20,2) NOT NULL,
    equipo_jugador VARCHAR(128) NOT NULL
);

-- PASO 4: Crear tabla de equipos
CREATE TABLE equipo(
    id_equipo INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre_equipo VARCHAR(128) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    presupuesto FLOAT(20,2) NOT NULL
);

-- PASO 5: Crear tabla de traspasos
CREATE TABLE traspaso(
    id_traspaso INT PRIMARY KEY AUTO_INCREMENT,
    nombre_jugador_traspaso VARCHAR(128) NOT NULL,
    nombre_equipo_origen VARCHAR(128) NOT NULL,
    nombre_equipo_destino VARCHAR(128) NOT NULL,
    precio FLOAT(20,2) NOT NULL
);

-- PASO 6: Verificar que se creó correctamente
SHOW TABLES;

-- ================================================
-- ¡BASE DE DATOS CREADA EXITOSAMENTE!
-- ================================================

-- COMANDOS ÚTILES PARA VERIFICAR:
-- DESCRIBE jugador;
-- DESCRIBE equipo;
-- DESCRIBE traspaso;

