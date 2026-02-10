-- ================================================
-- AÑADIR MÁS JUGADORES Y EQUIPOS
-- Ejecutar este script en MySQL después de datos_iniciales.sql
-- ================================================

USE fichajes;

-- ================================================
-- AÑADIR MÁS EQUIPOS
-- ================================================

-- Verificar que no existan primero
INSERT INTO equipo (nombre_equipo, ciudad, presupuesto)
SELECT 'Real Madrid', 'Madrid', 500000000.00
WHERE NOT EXISTS (SELECT 1 FROM equipo WHERE nombre_equipo = 'Real Madrid');

INSERT INTO equipo (nombre_equipo, ciudad, presupuesto)
SELECT 'Atlético Madrid', 'Madrid', 200000000.00
WHERE NOT EXISTS (SELECT 1 FROM equipo WHERE nombre_equipo = 'Atlético Madrid');

INSERT INTO equipo (nombre_equipo, ciudad, presupuesto)
SELECT 'Valencia CF', 'Valencia', 120000000.00
WHERE NOT EXISTS (SELECT 1 FROM equipo WHERE nombre_equipo = 'Valencia CF');

INSERT INTO equipo (nombre_equipo, ciudad, presupuesto)
SELECT 'Real Betis', 'Sevilla', 80000000.00
WHERE NOT EXISTS (SELECT 1 FROM equipo WHERE nombre_equipo = 'Real Betis');

-- ================================================
-- AÑADIR MÁS JUGADORES
-- ================================================

-- FC Barcelona
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Pedri', 21, 'Centrocampista', 80000000.00, 'FC Barcelona'),
('Gavi', 19, 'Centrocampista', 90000000.00, 'FC Barcelona'),
('Robert Lewandowski', 35, 'Delantero', 15000000.00, 'FC Barcelona'),
('Ronald Araújo', 25, 'Defensa', 70000000.00, 'FC Barcelona'),
('Ter Stegen', 31, 'Portero', 25000000.00, 'FC Barcelona');

-- Sevilla FC
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Youssef En-Nesyri', 26, 'Delantero', 25000000.00, 'Sevilla FC'),
('Jesús Navas', 38, 'Defensa', 2000000.00, 'Sevilla FC'),
('Lucas Ocampos', 29, 'Centrocampista', 12000000.00, 'Sevilla FC'),
('Bono', 32, 'Portero', 8000000.00, 'Sevilla FC');

-- Real Madrid
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Vinicius Jr', 23, 'Delantero', 120000000.00, 'Real Madrid'),
('Jude Bellingham', 20, 'Centrocampista', 150000000.00, 'Real Madrid'),
('Thibaut Courtois', 31, 'Portero', 45000000.00, 'Real Madrid'),
('Federico Valverde', 25, 'Centrocampista', 100000000.00, 'Real Madrid'),
('Rodrygo', 23, 'Delantero', 80000000.00, 'Real Madrid');

-- Atlético Madrid
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Antoine Griezmann', 32, 'Delantero', 30000000.00, 'Atlético Madrid'),
('Jan Oblak', 30, 'Portero', 40000000.00, 'Atlético Madrid'),
('Marcos Llorente', 28, 'Centrocampista', 50000000.00, 'Atlético Madrid'),
('José María Giménez', 29, 'Defensa', 35000000.00, 'Atlético Madrid');

-- Valencia CF
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('José Gayà', 28, 'Defensa', 20000000.00, 'Valencia CF'),
('Hugo Duro', 24, 'Delantero', 15000000.00, 'Valencia CF'),
('Pepelu', 25, 'Centrocampista', 12000000.00, 'Valencia CF');

-- Real Betis
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Nabil Fekir', 30, 'Centrocampista', 18000000.00, 'Real Betis'),
('Isco', 31, 'Centrocampista', 8000000.00, 'Real Betis'),
('Willian José', 32, 'Delantero', 5000000.00, 'Real Betis');

-- ================================================
-- VERIFICAR QUE SE AÑADIERON CORRECTAMENTE
-- ================================================

SELECT '=== TOTAL DE EQUIPOS ===' AS '';
SELECT COUNT(*) AS 'Total Equipos' FROM equipo;

SELECT '=== TOTAL DE JUGADORES ===' AS '';
SELECT COUNT(*) AS 'Total Jugadores' FROM jugador;

SELECT '=== TODOS LOS EQUIPOS ===' AS '';
SELECT * FROM equipo ORDER BY nombre_equipo;

SELECT '=== JUGADORES POR EQUIPO ===' AS '';
SELECT equipo_jugador, COUNT(*) as cantidad
FROM jugador
GROUP BY equipo_jugador
ORDER BY equipo_jugador;

SELECT '=== ALGUNOS JUGADORES ===' AS '';
SELECT nombre_jugador, edad, posicion, equipo_jugador
FROM jugador
ORDER BY equipo_jugador, nombre_jugador
LIMIT 10;

