-- ================================================
-- DATOS INICIALES: JUGADORES Y EQUIPOS
-- Ejecutar después de schema.sql
-- ================================================

USE fichajes;

-- Limpiar datos anteriores (opcional)
DELETE FROM traspaso;
DELETE FROM jugador;
DELETE FROM equipo;

-- ================================================
-- INSERTAR EQUIPOS
-- ================================================

INSERT INTO equipo (nombre_equipo, ciudad, presupuesto) VALUES
('FC Barcelona', 'Barcelona', 450000000.00),
('Sevilla FC', 'Sevilla', 150000000.00);

-- ================================================
-- INSERTAR JUGADORES
-- ================================================

-- Lamine Yamal (FC Barcelona)
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Lamine Yamal', 17, 'Extremo', 90000000.00, 'FC Barcelona');

-- Chidera Ejuke (Sevilla FC)
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Chidera Ejuke', 26, 'Extremo', 15000000.00, 'Sevilla FC');

-- ================================================
-- VERIFICAR QUE SE INSERTARON CORRECTAMENTE
-- ================================================

SELECT '=== EQUIPOS INSERTADOS ===' AS '';
SELECT * FROM equipo;

SELECT '=== JUGADORES INSERTADOS ===' AS '';
SELECT * FROM jugador;

SELECT '=== RESUMEN ===' AS '';
SELECT COUNT(*) AS 'Total Equipos' FROM equipo;
SELECT COUNT(*) AS 'Total Jugadores' FROM jugador;

