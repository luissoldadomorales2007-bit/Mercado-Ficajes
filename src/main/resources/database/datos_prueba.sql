# 📚 INSTRUCCIONES PARA CONFIGURAR LA BASE DE DATOS

## Paso 1: Instalar MySQL
Si no tienes MySQL instalado, descárgalo desde: https://dev.mysql.com/downloads/mysql/

## Paso 2: Ejecutar el script SQL

### Opción A: Desde MySQL Workbench (Más fácil para principiantes)
1. Abre **MySQL Workbench**
2. Conéctate a tu servidor local (usuario: `root`, contraseña: la que configuraste)
3. Ve a **File** → **Open SQL Script**
4. Busca y abre el archivo: `src/main/resources/database/schema.sql`
5. Haz clic en el icono del **rayo** ⚡ para ejecutar todo el script
6. ¡Listo! La base de datos `fichajes` ya está creada

### Opción B: Desde la línea de comandos
```bash
# En Windows, abre el símbolo del sistema (cmd) y ejecuta:
mysql -u root -p < src\main\resources\database\schema.sql
```
Te pedirá la contraseña de MySQL que configuraste.

### Opción C: Desde MySQL Command Line Client
1. Abre **MySQL Command Line Client** desde el menú inicio
2. Ingresa tu contraseña
3. Copia y pega todo el contenido del archivo `schema.sql`
4. Presiona Enter

## Paso 3: Verificar que se creó correctamente

Ejecuta estos comandos en MySQL:

```sql
-- Ver todas las bases de datos
SHOW DATABASES;

-- Debería aparecer 'fichajes' en la lista
USE fichajes;

-- Ver las tablas creadas
SHOW TABLES;

-- Deberías ver: jugador, equipo, traspaso
```

## Paso 4: Insertar datos de prueba (Opcional)

Puedes insertar algunos datos de ejemplo para probar:

```sql
USE fichajes;

-- Insertar equipos
INSERT INTO equipo (nombre_equipo, ciudad, presupuesto) VALUES
('Real Madrid', 'Madrid', 500000000.00),
('FC Barcelona', 'Barcelona', 450000000.00),
('Atlético Madrid', 'Madrid', 200000000.00);

-- Insertar jugadores
INSERT INTO jugador (nombre_jugador, edad, posicion, valor_mercado, equipo_jugador) VALUES
('Vinicius Jr', 23, 'Delantero', 120000000.00, 'Real Madrid'),
('Pedri', 21, 'Centrocampista', 80000000.00, 'FC Barcelona'),
('Griezmann', 32, 'Delantero', 30000000.00, 'Atlético Madrid');

-- Ver los datos
SELECT * FROM equipo;
SELECT * FROM jugador;
```

## 📝 Notas importantes para los estudiantes:

- **Usuario**: Por defecto es `root`
- **Contraseña**: La que configuraste al instalar MySQL
- **Puerto**: Por defecto es `3306`
- **Base de datos**: `fichajes`
- **Host**: `localhost` (tu ordenador)

## ❓ Problemas comunes:

1. **"Access denied for user"** → Verifica tu usuario y contraseña
2. **"Unknown database"** → Asegúrate de ejecutar todo el script schema.sql
3. **MySQL no se encuentra** → Verifica que MySQL esté instalado y en el PATH del sistema

---

**¡Ya tienes tu base de datos lista para conectar con Java!** 🎉

