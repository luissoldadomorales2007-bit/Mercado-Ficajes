# 🏆 MERCADO DE FICHAJES - Grupo 2

**Desarrolladores:** Fran y Alejandro  
**Asignatura:** Programación - Primer Curso  
**Proyecto:** Sistema de Gestión de Fichajes Deportivos

---

## 📋 ÍNDICE

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Estructura MVC](#estructura-mvc)
3. [Requisitos del Sistema](#requisitos-del-sistema)
4. [Instalación Paso a Paso](#instalación-paso-a-paso)
5. [Estructura de Carpetas](#estructura-de-carpetas)
6. [Base de Datos](#base-de-datos)
7. [Guía de Uso](#guía-de-uso)
8. [Git y GitHub](#git-y-github)

---

## 🎯 DESCRIPCIÓN DEL PROYECTO

Este proyecto es una aplicación de escritorio que permite gestionar:
- **Jugadores** de fútbol
- **Equipos** 
- **Traspasos** entre equipos

### Objetivo Educativo
Aprender a desarrollar una aplicación completa integrando:
- ✅ Java (POO - Programación Orientada a Objetos)
- ✅ JavaFX (Interfaz gráfica)
- ✅ MySQL (Base de datos)
- ✅ Git/GitHub (Control de versiones)
- ✅ Arquitectura MVC (Modelo-Vista-Controlador)

---

## 🏗️ ESTRUCTURA MVC

### ¿Qué es MVC?

**MVC** es un patrón de diseño que separa la aplicación en 3 partes:

```
┌─────────────────────────────────────────┐
│              USUARIO                     │
│         (Interfaz Gráfica)              │
└────────────────┬────────────────────────┘
                 │
                 ↓
┌────────────────────────────────────────┐
│            VISTA (View)                │
│        Archivos FXML + CSS             │
│  - MainView.fxml                       │
│  - JugadorView.fxml                    │
│  - EquipoView.fxml                     │
└────────────┬───────────────────────────┘
             │
             ↓
┌────────────────────────────────────────┐
│        CONTROLADOR (Controller)        │
│         Clases Java                    │
│  - MainController.java                 │
│  - JugadorController.java              │
│  - EquipoController.java               │
└────────────┬───────────────────────────┘
             │
             ↓
┌────────────────────────────────────────┐
│          MODELO (Model)                │
│    Lógica de negocio y datos           │
│  - Jugador.java (entidad)              │
│  - Equipo.java (entidad)               │
│  - Traspaso.java (entidad)             │
│  - JugadorDAO.java (base de datos)     │
└────────────┬───────────────────────────┘
             │
             ↓
┌────────────────────────────────────────┐
│         BASE DE DATOS MYSQL            │
│   - jugadores                          │
│   - equipos                            │
│   - traspasos                          │
└────────────────────────────────────────┘
```

### 📂 Cada parte tiene su función:

#### 1. **MODELO (Model)** - `src/main/java/com/mercadofichajes/model/`
- **Entidades**: Clases que representan los datos (Jugador, Equipo, Traspaso)
- **DAO**: Clases que se conectan a la base de datos
- **Servicios**: Lógica de negocio

#### 2. **VISTA (View)** - `src/main/resources/com/mercadofichajes/view/`
- Archivos **FXML**: Definen cómo se ve la interfaz
- Archivos **CSS**: Estilos de la aplicación

#### 3. **CONTROLADOR (Controller)** - `src/main/java/com/mercadofichajes/controller/`
- Une la Vista con el Modelo
- Maneja los eventos (clicks de botones, etc.)
- Actualiza la interfaz con los datos

---

## 💻 REQUISITOS DEL SISTEMA

### Software Necesario

1. **JDK 11 o superior**
   - Descargar desde: https://www.oracle.com/java/technologies/downloads/

2. **MySQL Server**
   - Descargar desde: https://dev.mysql.com/downloads/mysql/

3. **IDE** (Entorno de Desarrollo) - Elige uno:
   - IntelliJ IDEA Community (recomendado): https://www.jetbrains.com/idea/download/
   - Eclipse: https://www.eclipse.org/downloads/
   - NetBeans: https://netbeans.apache.org/

4. **Maven** (incluido en la mayoría de IDEs)

5. **Git**
   - Descargar desde: https://git-scm.com/downloads

---

## 🚀 INSTALACIÓN PASO A PASO

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/tuusuario/Mercado-Fichajes.git
cd Mercado-Fichajes
```

### Paso 2: Configurar la Base de Datos

1. Abre **MySQL Workbench** o el cliente MySQL
2. Ejecuta el script: `src/main/resources/database/schema.sql`

```sql
-- Esto creará:
-- - Base de datos: mercado_fichajes
-- - Tablas: jugadores, equipos, traspasos
-- - Datos de ejemplo
```

3. Verifica que se creó correctamente:
```sql
USE mercado_fichajes;
SHOW TABLES;
```

### Paso 3: Configurar la Conexión

Edita el archivo: `src/main/java/com/mercadofichajes/util/DatabaseConnection.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/mercado_fichajes";
private static final String USER = "root";  // Tu usuario MySQL
private static final String PASSWORD = "";  // Tu contraseña MySQL
```

### Paso 4: Compilar el Proyecto

En tu IDE:
- **IntelliJ IDEA**: Click derecho en el proyecto → "Reload Maven Project"
- **Eclipse**: Click derecho → Maven → Update Project

O desde terminal:
```bash
mvn clean install
```

### Paso 5: Ejecutar la Aplicación

Desde tu IDE:
- Ejecuta la clase `Main.java`

O desde terminal:
```bash
mvn javafx:run
```

---

## 📁 ESTRUCTURA DE CARPETAS

```
Mercado-Fichajes/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── mercadofichajes/
│       │           │
│       │           ├── Main.java                    ← PUNTO DE ENTRADA
│       │           │
│       │           ├── model/                       ← MODELO (M)
│       │           │   ├── entities/
│       │           │   │   ├── Jugador.java        ← Clase Jugador
│       │           │   │   ├── Equipo.java         ← Clase Equipo
│       │           │   │   └── Traspaso.java       ← Clase Traspaso
│       │           │   │
│       │           │   ├── dao/                    ← Acceso a BD
│       │           │   │   ├── JugadorDAO.java
│       │           │   │   ├── EquipoDAO.java
│       │           │   │   └── TraspasoDAO.java
│       │           │   │
│       │           │   └── services/               ← Lógica de negocio
│       │           │       ├── JugadorService.java
│       │           │       ├── EquipoService.java
│       │           │       └── TraspasoService.java
│       │           │
│       │           ├── view/                        ← VISTA (V) - FXML
│       │           │   ├── MainView.fxml
│       │           │   ├── JugadorListView.fxml
│       │           │   └── EquipoListView.fxml
│       │           │
│       │           ├── controller/                  ← CONTROLADOR (C)
│       │           │   ├── MainController.java
│       │           │   ├── JugadorListController.java
│       │           │   └── EquipoListController.java
│       │           │
│       │           └── util/                        ← Utilidades
│       │               ├── DatabaseConnection.java  ← Conexión BD
│       │               ├── AlertUtil.java          ← Alertas
│       │               └── ValidationUtil.java     ← Validaciones
│       │
│       └── resources/
│           ├── css/
│           │   └── style.css                        ← Estilos
│           └── database/
│               └── schema.sql                       ← Script SQL
│
├── pom.xml                                          ← Configuración Maven
├── README.md                                        ← Este archivo
└── .gitignore                                       ← Archivos ignorados
```

---

## 🗄️ BASE DE DATOS

### Diagrama de Tablas

```
┌─────────────────┐
│    EQUIPOS      │
├─────────────────┤
│ id (PK)         │
│ nombre          │
│ ciudad          │
│ presupuesto     │
└────────┬────────┘
         │
         │ 1:N
         │
┌────────┴────────┐
│   JUGADORES     │
├─────────────────┤
│ id (PK)         │
│ nombre          │
│ edad            │
│ posicion        │
│ valor_mercado   │
│ equipo_id (FK)  │◄──┐
└────────┬────────┘   │
         │            │
         │ 1:N        │
         │            │
┌────────┴────────┐   │
│   TRASPASOS     │   │
├─────────────────┤   │
│ id (PK)         │   │
│ jugador_id (FK) │───┘
│ equipo_origen_id│
│ equipo_destino_id
│ precio          │
│ fecha           │
└─────────────────┘
```

### Tablas

#### 1. **equipos**
- `id`: Identificador único
- `nombre`: Nombre del equipo
- `ciudad`: Ciudad del equipo
- `presupuesto`: Presupuesto disponible

#### 2. **jugadores**
- `id`: Identificador único
- `nombre`: Nombre del jugador
- `edad`: Edad del jugador
- `posicion`: Posición en el campo
- `valor_mercado`: Valor del jugador
- `equipo_id`: Equipo al que pertenece

#### 3. **traspasos**
- `id`: Identificador único
- `jugador_id`: Jugador traspasado
- `equipo_origen_id`: Equipo de origen
- `equipo_destino_id`: Equipo de destino
- `precio`: Precio del traspaso
- `fecha`: Fecha del traspaso

---

## 📖 GUÍA DE USO

### Funcionalidades Implementadas

#### 1️⃣ **Gestión de Jugadores**
- ➕ Añadir jugador nuevo
- 📋 Ver lista de todos los jugadores
- ✏️ Editar datos de un jugador
- 🗑️ Eliminar jugador
- 🔍 Buscar jugadores

#### 2️⃣ **Gestión de Equipos**
- ➕ Crear equipo nuevo
- 📋 Ver lista de equipos
- ✏️ Modificar equipo
- 🗑️ Eliminar equipo

#### 3️⃣ **Gestión de Traspasos**
- ➕ Registrar traspaso
- 📋 Ver historial de traspasos
- 🔍 Consultar traspasos

---

## 📚 GIT Y GITHUB

### Flujo de Trabajo Básico

#### 1. **Clonar el Repositorio**
```bash
git clone https://github.com/tuusuario/Mercado-Fichajes.git
```

#### 2. **Crear una Rama para Trabajar**
```bash
git checkout -b feature/nombre-funcionalidad
```

Ejemplo:
```bash
git checkout -b feature/agregar-jugadores
```

#### 3. **Hacer Cambios y Guardarlos**
```bash
# Ver qué archivos cambiaron
git status

# Añadir archivos al staging
git add .

# Hacer commit
git commit -m "Descripción clara de los cambios"
```

#### 4. **Subir Cambios a GitHub**
```bash
git push origin feature/nombre-funcionalidad
```

#### 5. **Crear Pull Request**
- Ve a GitHub
- Click en "Pull Request"
- Describe los cambios
- Solicita revisión a tu compañero

#### 6. **Actualizar tu Rama Local**
```bash
# Cambiar a main
git checkout main

# Traer cambios
git pull origin main
```

### 🌿 Estrategia de Ramas

```
main (rama principal)
  ├── feature/gestion-jugadores
  ├── feature/gestion-equipos
  └── feature/gestion-traspasos
```

---

## 🎓 CONCEPTOS CLAVE PARA APRENDER

### 1. **Programación Orientada a Objetos (POO)**
- **Clase**: Plantilla (ejemplo: Jugador.java)
- **Objeto**: Instancia de una clase (ejemplo: Messi)
- **Atributos**: Características (nombre, edad)
- **Métodos**: Acciones (getNombre(), setNombre())

### 2. **CRUD** (Create, Read, Update, Delete)
- **Create**: Añadir nuevo registro
- **Read**: Leer/consultar datos
- **Update**: Actualizar registro
- **Delete**: Eliminar registro

### 3. **DAO** (Data Access Object)
- Patrón para separar la lógica de acceso a datos
- Una clase DAO por cada tabla de la BD

### 4. **FXML**
- Lenguaje XML para definir interfaces gráficas en JavaFX
- Separa el diseño de la lógica

---

## ✅ LISTA DE TAREAS

### Fase 1: Configuración Inicial
- [ ] Instalar JDK
- [ ] Instalar MySQL
- [ ] Instalar IDE
- [ ] Clonar repositorio
- [ ] Crear base de datos

### Fase 2: Gestión de Equipos
- [ ] Crear tabla equipos
- [ ] Clase Equipo.java
- [ ] EquipoDAO.java
- [ ] EquipoService.java
- [ ] EquipoController.java
- [ ] EquipoView.fxml

### Fase 3: Gestión de Jugadores
- [ ] Crear tabla jugadores
- [ ] Clase Jugador.java
- [ ] JugadorDAO.java
- [ ] JugadorService.java
- [ ] JugadorController.java
- [ ] JugadorView.fxml

### Fase 4: Gestión de Traspasos
- [ ] Crear tabla traspasos
- [ ] Clase Traspaso.java
- [ ] TraspasoDAO.java
- [ ] TraspasoService.java
- [ ] TraspasoController.java
- [ ] TraspasoView.fxml

### Fase 5: Pruebas y Documentación
- [ ] Probar todas las funcionalidades
- [ ] Documentar el código
- [ ] Crear manual de usuario
- [ ] Presentación del proyecto

---

## 🤝 COLABORADORES

- **Fran**: [GitHub Profile]
- **Alejandro**: [GitHub Profile]

---

## 📞 AYUDA Y RECURSOS

### Documentación Oficial
- Java: https://docs.oracle.com/en/java/
- JavaFX: https://openjfx.io/
- MySQL: https://dev.mysql.com/doc/
- Maven: https://maven.apache.org/guides/

### Tutoriales Recomendados
- JavaFX Tutorial: https://jenkov.com/tutorials/javafx/index.html
- JDBC Tutorial: https://www.baeldung.com/java-jdbc
- Git Básico: https://www.atlassian.com/git/tutorials

---

## 📄 LICENCIA

Este es un proyecto educativo desarrollado para fines de aprendizaje.

---

**¡Buena suerte con el proyecto! 🚀⚽**

