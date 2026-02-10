# 📚 GUÍA DIDÁCTICA PASO A PASO
## Mercado de Fichajes - Para Estudiantes Principiantes

**Grupo 2:** Fran y Alejandro

---

## 🎯 ÍNDICE DE LA GUÍA

1. [Introducción](#1-introducción)
2. [Preparación del Entorno](#2-preparación-del-entorno)
3. [Entendiendo la Estructura MVC](#3-entendiendo-la-estructura-mvc)
4. [Fase 1: Base de Datos](#4-fase-1-base-de-datos)
5. [Fase 2: Modelo de Datos](#5-fase-2-modelo-de-datos)
6. [Fase 3: Acceso a Datos (DAO)](#6-fase-3-acceso-a-datos-dao)
7. [Fase 4: Servicios](#7-fase-4-servicios)
8. [Fase 5: Vista (FXML)](#8-fase-5-vista-fxml)
9. [Fase 6: Controlador](#9-fase-6-controlador)
10. [Fase 7: Pruebas y Git](#10-fase-7-pruebas-y-git)

---

## 1. INTRODUCCIÓN

### ¿Qué vamos a hacer?

Vamos a crear una aplicación para gestionar **fichajes de fútbol** donde podremos:
- ✅ Ver, añadir, editar y eliminar **jugadores**
- ✅ Ver, añadir, editar y eliminar **equipos**
- ✅ Registrar **traspasos** de jugadores entre equipos

### ¿Qué vamos a aprender?

- 📘 **Java**: Programación orientada a objetos
- 🖼️ **JavaFX**: Crear interfaces gráficas
- 🗄️ **MySQL**: Bases de datos
- 🏗️ **MVC**: Arquitectura de software
- 🌿 **Git/GitHub**: Control de versiones

---

## 2. PREPARACIÓN DEL ENTORNO

### Paso 1: Instalar JDK 11

1. Ir a https://www.oracle.com/java/technologies/downloads/
2. Descargar JDK 11 para Windows
3. Ejecutar el instalador
4. Verificar instalación: Abrir CMD y escribir:
```bash
java -version
```

### Paso 2: Instalar MySQL

1. Ir a https://dev.mysql.com/downloads/installer/
2. Descargar MySQL Installer
3. Durante la instalación, anotar:
   - **Usuario**: root
   - **Contraseña**: (la que elijas)
4. Instalar también MySQL Workbench

### Paso 3: Instalar IntelliJ IDEA

1. Ir a https://www.jetbrains.com/idea/download/
2. Descargar Community Edition (gratis)
3. Instalar con opciones por defecto

### Paso 4: Instalar Git

1. Ir a https://git-scm.com/downloads
2. Instalar con opciones por defecto
3. Verificar: Abrir CMD y escribir:
```bash
git --version
```

---

## 3. ENTENDIENDO LA ESTRUCTURA MVC

### ¿Qué es MVC?

**MVC = Modelo + Vista + Controlador**

Es como construir una casa en 3 partes:

```
┌──────────────────────────────────────┐
│   VISTA (View) - La fachada          │
│   Lo que el usuario VE               │
│   → Botones, tablas, formularios     │
└──────────┬───────────────────────────┘
           │
           ↓
┌──────────────────────────────────────┐
│   CONTROLADOR (Controller)           │
│   El cerebro - La lógica             │
│   → Maneja clicks, eventos           │
└──────────┬───────────────────────────┘
           │
           ↓
┌──────────────────────────────────────┐
│   MODELO (Model) - Los datos         │
│   Donde se GUARDAN los datos         │
│   → Jugadores, equipos, traspasos    │
└──────────────────────────────────────┘
```

### Ventajas de MVC

✅ **Organización**: Cada cosa en su lugar
✅ **Fácil de mantener**: Cambias una parte sin romper otra
✅ **Trabajo en equipo**: Uno hace la vista, otro el modelo
✅ **Reutilizable**: Puedes usar el modelo en otra aplicación

---

## 4. FASE 1: BASE DE DATOS

### 🎯 Objetivo
Crear las tablas en MySQL para guardar nuestros datos

### Paso 1: Abrir MySQL Workbench

1. Abrir MySQL Workbench
2. Conectar a tu servidor local
3. Usuario: `root`
4. Contraseña: la que configuraste

### Paso 2: Ejecutar el Script

1. En el proyecto, ir a: `src/main/resources/database/schema.sql`
2. Copiar TODO el contenido
3. En MySQL Workbench, pegar el código
4. Click en el rayo ⚡ (Execute)

### Paso 3: Verificar que funciona

Ejecutar estas consultas:

```sql
-- Ver qué bases de datos tenemos
SHOW DATABASES;

-- Usar nuestra base de datos
USE mercado_fichajes;

-- Ver las tablas
SHOW TABLES;

-- Ver los equipos de ejemplo
SELECT * FROM equipos;

-- Ver los jugadores de ejemplo
SELECT * FROM jugadores;
```

### 📝 Explicación de las Tablas

#### Tabla EQUIPOS
```sql
CREATE TABLE equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Número único para cada equipo
    nombre VARCHAR(100) NOT NULL,       -- Nombre del equipo
    ciudad VARCHAR(100) NOT NULL,       -- Ciudad donde está
    presupuesto DECIMAL(15, 2)          -- Dinero disponible
);
```

#### Tabla JUGADORES
```sql
CREATE TABLE jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Número único
    nombre VARCHAR(100) NOT NULL,       -- Nombre del jugador
    edad INT NOT NULL,                  -- Edad
    posicion VARCHAR(50) NOT NULL,      -- Posición (Portero, Defensa, etc.)
    valor_mercado DECIMAL(12, 2),       -- Cuánto vale el jugador
    equipo_id INT,                      -- A qué equipo pertenece
    FOREIGN KEY (equipo_id) REFERENCES equipos(id)  -- Relación con equipos
);
```

#### Tabla TRASPASOS
```sql
CREATE TABLE traspasos (
    id INT AUTO_INCREMENT PRIMARY KEY,    -- Número único
    jugador_id INT NOT NULL,              -- Qué jugador se traspasa
    equipo_origen_id INT,                 -- De qué equipo viene
    equipo_destino_id INT NOT NULL,       -- A qué equipo va
    precio DECIMAL(12, 2) NOT NULL,       -- Precio del traspaso
    fecha_traspaso DATE,                  -- Cuándo se hizo
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id),
    FOREIGN KEY (equipo_origen_id) REFERENCES equipos(id),
    FOREIGN KEY (equipo_destino_id) REFERENCES equipos(id)
);
```

---

## 5. FASE 2: MODELO DE DATOS

### 🎯 Objetivo
Crear las clases Java que representan nuestros datos

### ¿Qué son las entidades?

Una **entidad** es una clase Java que representa algo del mundo real.

**Ejemplo:**
- Un jugador es una entidad
- Tiene propiedades: nombre, edad, posición
- Tiene comportamientos: getNombre(), setNombre()

### Archivo: `Jugador.java`

**Ubicación:** `src/main/java/com/mercadofichajes/model/entities/Jugador.java`

```java
public class Jugador {
    // ATRIBUTOS (características del jugador)
    private int id;
    private String nombre;
    private int edad;
    private String posicion;
    private double valorMercado;
    private int equipoId;
    
    // CONSTRUCTOR VACÍO
    public Jugador() {
    }
    
    // CONSTRUCTOR CON PARÁMETROS
    public Jugador(int id, String nombre, int edad, String posicion, 
                   double valorMercado, int equipoId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.valorMercado = valorMercado;
        this.equipoId = equipoId;
    }
    
    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    // ... más getters y setters para cada atributo
}
```

### 📝 Conceptos Importantes

#### ¿Qué es `private`?
- **private**: Solo esta clase puede ver estos datos
- Es como tener un diario con candado 🔒

#### ¿Qué son los Getters y Setters?
- **Getter**: Para LEER un dato (`getNombre()`)
- **Setter**: Para CAMBIAR un dato (`setNombre("Messi")`)

**Ejemplo de uso:**
```java
// Crear un jugador
Jugador jugador = new Jugador();

// Establecer valores (SETTERS)
jugador.setNombre("Lionel Messi");
jugador.setEdad(36);
jugador.setPosicion("Delantero");
jugador.setValorMercado(35000000);

// Obtener valores (GETTERS)
String nombre = jugador.getNombre();  // "Lionel Messi"
int edad = jugador.getEdad();          // 36
```

### Ejercicio Práctico 1

**Tarea:** Revisar las clases `Equipo.java` y `Transferencia.java`

1. Abrir `Equipo.java`
2. Identificar:
   - ¿Cuántos atributos tiene?
   - ¿Cuáles son sus getters?
   - ¿Cuáles son sus setters?

---

## 6. FASE 3: ACCESO A DATOS (DAO)

### 🎯 Objetivo
Crear clases que se conecten a la base de datos

### ¿Qué es un DAO?

**DAO = Data Access Object**

Es una clase que sabe cómo:
- ✅ GUARDAR datos en la base de datos
- ✅ LEER datos de la base de datos
- ✅ ACTUALIZAR datos
- ✅ ELIMINAR datos

Es como un **mensajero** entre Java y MySQL.

### Paso 1: Conexión a la Base de Datos

**Archivo:** `DatabaseConnection.java`

```java
public class DatabaseConnection {
    // Configuración de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/mercado_fichajes";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";
    
    // Método para obtener la conexión
    public Connection getConnection() {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
}
```

### Paso 2: JugadorDAO - CRUD Completo

**CRUD = Create, Read, Update, Delete**

```java
public class JugadorDAO {
    
    // CREATE - Insertar un nuevo jugador
    public boolean save(Jugador jugador) {
        String sql = "INSERT INTO jugadores (nombre, edad, posicion, valor_mercado, equipo_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        // Preparar la consulta
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, jugador.getNombre());
        pstmt.setInt(2, jugador.getEdad());
        pstmt.setString(3, jugador.getPosicion());
        pstmt.setDouble(4, jugador.getValorMercado());
        pstmt.setInt(5, jugador.getEquipoId());
        
        // Ejecutar
        return pstmt.executeUpdate() > 0;
    }
    
    // READ - Obtener todos los jugadores
    public List<Jugador> findAll() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            Jugador jugador = new Jugador();
            jugador.setId(rs.getInt("id"));
            jugador.setNombre(rs.getString("nombre"));
            jugador.setEdad(rs.getInt("edad"));
            jugador.setPosicion(rs.getString("posicion"));
            jugador.setValorMercado(rs.getDouble("valor_mercado"));
            jugador.setEquipoId(rs.getInt("equipo_id"));
            
            jugadores.add(jugador);
        }
        
        return jugadores;
    }
    
    // UPDATE - Actualizar un jugador
    public boolean update(Jugador jugador) {
        String sql = "UPDATE jugadores SET nombre = ?, edad = ?, " +
                    "posicion = ?, valor_mercado = ?, equipo_id = ? WHERE id = ?";
        // ... código similar al save
    }
    
    // DELETE - Eliminar un jugador
    public boolean delete(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        // ... código para ejecutar
    }
}
```

### 📝 Explicación de Prepared Statement

```java
String sql = "INSERT INTO jugadores (nombre, edad) VALUES (?, ?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "Messi");  // Primer ?
pstmt.setInt(2, 36);           // Segundo ?
```

Los `?` son **placeholders** (marcadores de posición).
- Evitan **SQL Injection** (ataques)
- Son más seguros

### Ejercicio Práctico 2

**Tarea:** Completar `EquipoDAO.java`

1. Implementar el método `findById(int id)`
2. Debe devolver un equipo específico
3. Usar `SELECT * FROM equipos WHERE id = ?`

---

## 7. FASE 4: SERVICIOS

### 🎯 Objetivo
Crear la lógica de negocio de la aplicación

### ¿Qué es un Service?

Un **Service** es una capa intermedia que:
- Usa los DAOs para acceder a datos
- Aplica **validaciones**
- Aplica **reglas de negocio**

**Ejemplo:**
```java
public class JugadorService {
    private JugadorDAO jugadorDAO;
    
    public JugadorService() {
        this.jugadorDAO = new JugadorDAO();
    }
    
    // Crear jugador CON VALIDACIONES
    public boolean crearJugador(Jugador jugador) {
        // VALIDAR: El nombre no puede estar vacío
        if (jugador.getNombre() == null || jugador.getNombre().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }
        
        // VALIDAR: La edad debe estar entre 16 y 45
        if (jugador.getEdad() < 16 || jugador.getEdad() > 45) {
            throw new Exception("Edad inválida");
        }
        
        // VALIDAR: El valor no puede ser negativo
        if (jugador.getValorMercado() < 0) {
            throw new Exception("El valor no puede ser negativo");
        }
        
        // Si todo está bien, guardar
        return jugadorDAO.save(jugador);
    }
    
    // Obtener todos
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorDAO.findAll();
    }
}
```

### ¿Por qué usar Services?

✅ **Validación centralizada**: Un solo lugar para todas las reglas
✅ **Código limpio**: El DAO solo se encarga de la BD
✅ **Fácil de testear**: Puedes probar las validaciones
✅ **Reutilizable**: Otros controladores pueden usar el mismo servicio

---

## 8. FASE 5: VISTA (FXML)

### 🎯 Objetivo
Crear la interfaz gráfica con JavaFX

### ¿Qué es FXML?

**FXML** es un lenguaje XML para diseñar interfaces gráficas.

**Ventaja:** Separa el diseño (XML) de la lógica (Java)

### Ejemplo Básico: Formulario de Jugador

```xml
<?xml version="1.0" encoding="UTF-8"?>
<VBox xmlns:fx="http://javafx.com/fxml">
    
    <!-- Título -->
    <Label text="Añadir Jugador" style="-fx-font-size: 20px;"/>
    
    <!-- Formulario -->
    <GridPane>
        <!-- Campo Nombre -->
        <Label text="Nombre:" GridPane.columnIndex="0" GridPane.rowIndex="0"/>
        <TextField fx:id="txtNombre" GridPane.columnIndex="1" GridPane.rowIndex="0"/>
        
        <!-- Campo Edad -->
        <Label text="Edad:" GridPane.columnIndex="0" GridPane.rowIndex="1"/>
        <TextField fx:id="txtEdad" GridPane.columnIndex="1" GridPane.rowIndex="1"/>
        
        <!-- Botón Guardar -->
        <Button text="Guardar" onAction="#guardarJugador"/>
    </GridPane>
    
    <!-- Tabla de Jugadores -->
    <TableView fx:id="tablaJugadores">
        <columns>
            <TableColumn fx:id="colNombre" text="Nombre"/>
            <TableColumn fx:id="colEdad" text="Edad"/>
            <TableColumn fx:id="colPosicion" text="Posición"/>
        </columns>
    </TableView>
    
</VBox>
```

### 📝 Elementos Importantes

#### `fx:id` - Identificador
```xml
<TextField fx:id="txtNombre"/>
```
- El `fx:id` es como el **nombre** del elemento
- Lo usarás en Java para acceder a él

#### `onAction` - Evento
```xml
<Button text="Guardar" onAction="#guardarJugador"/>
```
- Cuando haces click, llama al método `guardarJugador()`

#### `fx:controller` - Vinculación
```xml
<VBox fx:controller="com.mercadofichajes.controller.JugadorController">
```
- Indica qué clase Java controla esta vista

---

## 9. FASE 6: CONTROLADOR

### 🎯 Objetivo
Conectar la vista con el modelo

### ¿Qué hace un Controller?

1. **Recibe eventos** de la vista (clicks, escribir texto)
2. **Llama al servicio** para obtener/guardar datos
3. **Actualiza la vista** con los nuevos datos

### Ejemplo: JugadorController

```java
public class JugadorController {
    
    // ELEMENTOS DE LA VISTA (vinculados por fx:id)
    @FXML private TextField txtNombre;
    @FXML private TextField txtEdad;
    @FXML private TextField txtPosicion;
    @FXML private TextField txtValorMercado;
    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    
    // SERVICIO
    private JugadorService jugadorService;
    
    // INICIALIZACIÓN
    @FXML
    public void initialize() {
        jugadorService = new JugadorService();
        configurarTabla();
        cargarJugadores();
    }
    
    // CONFIGURAR LA TABLA
    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }
    
    // CARGAR JUGADORES EN LA TABLA
    private void cargarJugadores() {
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();
        ObservableList<Jugador> data = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(data);
    }
    
    // GUARDAR JUGADOR (vinculado al botón)
    @FXML
    private void guardarJugador() {
        try {
            // 1. OBTENER datos del formulario
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String posicion = txtPosicion.getText();
            double valor = Double.parseDouble(txtValorMercado.getText());
            
            // 2. CREAR objeto Jugador
            Jugador jugador = new Jugador();
            jugador.setNombre(nombre);
            jugador.setEdad(edad);
            jugador.setPosicion(posicion);
            jugador.setValorMercado(valor);
            
            // 3. GUARDAR usando el servicio
            if (jugadorService.crearJugador(jugador)) {
                // 4. ACTUALIZAR la tabla
                cargarJugadores();
                
                // 5. LIMPIAR el formulario
                limpiarFormulario();
                
                // 6. MOSTRAR mensaje de éxito
                mostrarMensaje("Jugador guardado correctamente");
            }
        } catch (Exception e) {
            mostrarError("Error: " + e.getMessage());
        }
    }
    
    // LIMPIAR FORMULARIO
    private void limpiarFormulario() {
        txtNombre.clear();
        txtEdad.clear();
        txtPosicion.clear();
        txtValorMercado.clear();
    }
}
```

### 📝 Anotación @FXML

```java
@FXML private TextField txtNombre;
```

- `@FXML` indica que este campo está vinculado al FXML
- El nombre debe coincidir con el `fx:id` del FXML

---

## 10. FASE 7: PRUEBAS Y GIT

### 🎯 Objetivo
Probar la aplicación y subir a GitHub

### Paso 1: Ejecutar la Aplicación

1. En IntelliJ, ir a `Main.java`
2. Click derecho → Run 'Main'
3. Debe abrir la ventana

### Paso 2: Probar Funcionalidades

**Checklist de pruebas:**

#### Jugadores
- [ ] Añadir un jugador
- [ ] Ver lista de jugadores
- [ ] Editar un jugador
- [ ] Eliminar un jugador
- [ ] Buscar un jugador

#### Equipos
- [ ] Añadir un equipo
- [ ] Ver lista de equipos
- [ ] Editar un equipo
- [ ] Eliminar un equipo

#### Traspasos
- [ ] Registrar un traspaso
- [ ] Ver historial de traspasos

### Paso 3: Git y GitHub

#### Inicializar Git

```bash
# Abrir terminal en la carpeta del proyecto
cd C:\Users\robeb_e\Desktop\Mercado-Ficajes

# Inicializar Git
git init

# Añadir todos los archivos
git add .

# Primer commit
git commit -m "Proyecto inicial - Estructura MVC"
```

#### Crear Repositorio en GitHub

1. Ir a https://github.com
2. Click en "New repository"
3. Nombre: `Mercado-Fichajes`
4. Click "Create repository"

#### Subir código a GitHub

```bash
# Conectar con GitHub
git remote add origin https://github.com/TU_USUARIO/Mercado-Fichajes.git

# Subir el código
git push -u origin main
```

#### Trabajar en Ramas

```bash
# Crear rama para nueva funcionalidad
git checkout -b feature/agregar-traspasos

# Hacer cambios...
# Añadir archivos modificados
git add .

# Commit
git commit -m "Añadida gestión de traspasos"

# Subir la rama
git push origin feature/agregar-traspasos
```

---

## ✅ RESUMEN FINAL

### Lo que has aprendido

1. ✅ **POO en Java**: Clases, objetos, getters, setters
2. ✅ **Base de Datos**: SQL, tablas, relaciones
3. ✅ **MVC**: Separación de responsabilidades
4. ✅ **JavaFX**: Interfaces gráficas
5. ✅ **Git**: Control de versiones

### Próximos pasos

1. Añadir más validaciones
2. Mejorar el diseño con CSS
3. Añadir búsqueda avanzada
4. Generar reportes
5. Exportar datos a PDF

---

## 🆘 PREGUNTAS FRECUENTES

### ¿Qué hago si no se conecta a la base de datos?

1. Verificar que MySQL esté ejecutándose
2. Comprobar usuario y contraseña en `DatabaseConnection.java`
3. Verificar que la base de datos existe: `SHOW DATABASES;`

### ¿Qué hago si Maven no descarga las dependencias?

1. Click derecho en el proyecto
2. Maven → Reload Project
3. Si no funciona, borrar la carpeta `.m2` y volver a cargar

### ¿Cómo agrego más campos a Jugador?

1. Añadir columna en la BD
2. Añadir atributo en `Jugador.java`
3. Añadir getter y setter
4. Actualizar `JugadorDAO.java`
5. Actualizar FXML
6. Actualizar Controller

---

**¡Éxito con el proyecto! 🚀⚽**

*Cualquier duda, consultar con el profesor o buscar en la documentación oficial.*

