-- ============================================
-- MERCADO DE FICHAJES - BASE DE DATOS
-- Script de creación para MySQL
-- Grupo 2: Fran y Alejandro
-- ============================================

-- Crear la base de datos
DROP DATABASE IF EXISTS mercado_fichajes;
CREATE DATABASE mercado_fichajes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mercado_fichajes;

-- ============================================
-- TABLA: EQUIPOS
-- Almacena información de los equipos
-- ============================================
CREATE TABLE equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    ciudad VARCHAR(100) NOT NULL,
    presupuesto DECIMAL(15, 2) DEFAULT 0,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nombre (nombre)
) ENGINE=InnoDB;

-- ============================================
-- TABLA: JUGADORES
-- Almacena información de los jugadores
-- ============================================
CREATE TABLE jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    posicion VARCHAR(50) NOT NULL,
    valor_mercado DECIMAL(12, 2) NOT NULL DEFAULT 0,
    equipo_id INT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (equipo_id) REFERENCES equipos(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_nombre (nombre),
    INDEX idx_equipo (equipo_id),
    CHECK (edad >= 16 AND edad <= 45),
    CHECK (valor_mercado >= 0)
) ENGINE=InnoDB;

-- ============================================
-- TABLA: TRASPASOS
-- Registra los movimientos de jugadores entre equipos
-- ============================================
CREATE TABLE traspasos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador_id INT NOT NULL,
    equipo_origen_id INT,
    equipo_destino_id INT NOT NULL,
    precio DECIMAL(12, 2) NOT NULL,
    fecha_traspaso DATE NOT NULL DEFAULT (CURRENT_DATE),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (equipo_origen_id) REFERENCES equipos(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (equipo_destino_id) REFERENCES equipos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_jugador (jugador_id),
    INDEX idx_fecha (fecha_traspaso),
    CHECK (precio >= 0),
    CHECK (equipo_origen_id != equipo_destino_id OR equipo_origen_id IS NULL)
) ENGINE=InnoDB;

-- ============================================
-- DATOS DE EJEMPLO PARA PRUEBAS
-- ============================================

-- Insertar equipos de ejemplo
INSERT INTO equipos (nombre, ciudad, presupuesto) VALUES
('Real Madrid', 'Madrid', 500000000.00),
('FC Barcelona', 'Barcelona', 450000000.00),
('Atlético de Madrid', 'Madrid', 300000000.00),
('Sevilla FC', 'Sevilla', 150000000.00),
('Valencia CF', 'Valencia', 120000000.00);

-- Insertar jugadores de ejemplo
INSERT INTO jugadores (nombre, edad, posicion, valor_mercado, equipo_id) VALUES
-- Real Madrid
('Vinícius Jr', 23, 'Delantero', 120000000.00, 1),
('Jude Bellingham', 20, 'Centrocampista', 150000000.00, 1),
('Thibaut Courtois', 31, 'Portero', 45000000.00, 1),
('Dani Carvajal', 31, 'Defensa', 15000000.00, 1),

-- FC Barcelona
('Robert Lewandowski', 35, 'Delantero', 35000000.00, 2),
('Pedri', 21, 'Centrocampista', 100000000.00, 2),
('Gavi', 19, 'Centrocampista', 90000000.00, 2),
('Ter Stegen', 31, 'Portero', 40000000.00, 2),

-- Atlético de Madrid
('Antoine Griezmann', 32, 'Delantero', 30000000.00, 3),
('Koke', 31, 'Centrocampista', 15000000.00, 3),
('Jan Oblak', 30, 'Portero', 45000000.00, 3),

-- Sevilla FC
('Youssef En-Nesyri', 26, 'Delantero', 25000000.00, 4),
('Ivan Rakitic', 35, 'Centrocampista', 5000000.00, 4),

-- Valencia CF
('Hugo Duro', 24, 'Delantero', 15000000.00, 5),
('José Gayà', 28, 'Defensa', 20000000.00, 5);

-- Insertar algunos traspasos de ejemplo
INSERT INTO traspasos (jugador_id, equipo_origen_id, equipo_destino_id, precio, fecha_traspaso) VALUES
(1, NULL, 1, 45000000.00, '2023-07-01'),  -- Vinícius Jr fichaje inicial por el Real Madrid
(2, NULL, 1, 103000000.00, '2023-06-14'), -- Bellingham al Real Madrid
(5, NULL, 2, 45000000.00, '2022-07-19'),  -- Lewandowski al Barcelona
(9, 2, 3, 0.00, '2021-07-01');            -- Griezmann vuelve al Atlético (cesión)

-- ============================================
-- VISTAS ÚTILES PARA CONSULTAS
-- ============================================

-- Vista para ver jugadores con su equipo
CREATE OR REPLACE VIEW vista_jugadores AS
SELECT
    j.id,
    j.nombre,
    j.edad,
    j.posicion,
    j.valor_mercado,
    e.nombre AS equipo,
    e.ciudad AS ciudad_equipo
FROM jugadores j
LEFT JOIN equipos e ON j.equipo_id = e.id;

-- Vista para ver traspasos completos
CREATE OR REPLACE VIEW vista_traspasos AS
SELECT
    t.id,
    j.nombre AS jugador,
    eo.nombre AS equipo_origen,
    ed.nombre AS equipo_destino,
    t.precio,
    t.fecha_traspaso
FROM traspasos t
JOIN jugadores j ON t.jugador_id = j.id
LEFT JOIN equipos eo ON t.equipo_origen_id = eo.id
JOIN equipos ed ON t.equipo_destino_id = ed.id
ORDER BY t.fecha_traspaso DESC;

-- ============================================
-- PROCEDIMIENTO PARA REALIZAR UN TRASPASO
-- ============================================
DELIMITER //

CREATE PROCEDURE realizar_traspaso(
    IN p_jugador_id INT,
    IN p_equipo_destino_id INT,
    IN p_precio DECIMAL(12, 2)
)
BEGIN
    DECLARE v_equipo_origen_id INT;

    -- Obtener el equipo actual del jugador
    SELECT equipo_id INTO v_equipo_origen_id
    FROM jugadores
    WHERE id = p_jugador_id;

    -- Registrar el traspaso
    INSERT INTO traspasos (jugador_id, equipo_origen_id, equipo_destino_id, precio, fecha_traspaso)
    VALUES (p_jugador_id, v_equipo_origen_id, p_equipo_destino_id, p_precio, CURDATE());

    -- Actualizar el equipo del jugador
    UPDATE jugadores
    SET equipo_id = p_equipo_destino_id
    WHERE id = p_jugador_id;

    -- Actualizar presupuestos (opcional)
    IF v_equipo_origen_id IS NOT NULL THEN
        UPDATE equipos SET presupuesto = presupuesto + p_precio WHERE id = v_equipo_origen_id;
    END IF;
    UPDATE equipos SET presupuesto = presupuesto - p_precio WHERE id = p_equipo_destino_id;
END //

DELIMITER ;

-- ============================================
-- CONSULTAS DE VERIFICACIÓN
-- ============================================

-- Ver todos los equipos
-- SELECT * FROM equipos;

-- Ver todos los jugadores con su equipo
-- SELECT * FROM vista_jugadores;

-- Ver todos los traspasos
-- SELECT * FROM vista_traspasos;

-- Ver jugadores por equipo
-- SELECT nombre, edad, posicion, valor_mercado
-- FROM jugadores
-- WHERE equipo_id = 1;

-- COMMIT para guardar todos los cambios
COMMIT;

-- Mensaje de éxito
SELECT 'Base de datos creada exitosamente' AS mensaje;

