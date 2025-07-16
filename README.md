# DriveQuest Rentals

## Índice

- [DriveQuest Rentals](#drivequest-rentals)
  - [Índice](#índice)
  - [1. Propósito del Proyecto](#1-propósito-del-proyecto)
  - [2. Estructura Principal](#2-estructura-principal)
  - [3. Flujo Básico de Uso](#3-flujo-básico-de-uso)
  - [4. Concurrencia y Seguridad](#4-concurrencia-y-seguridad)
  - [5. Extensión y Mantenimiento](#5-extensión-y-mantenimiento)


---

## 1. Propósito del Proyecto

DriveQuest Rentals es una aplicación Java para la gestión de arriendo de vehículos, tanto de pasajeros como de carga. Permite agregar, listar, arrendar y mostrar boletas de vehículos, todo desde consola y usando archivos CSV para persistencia.

---

## 2. Estructura Principal

- **VehiculoController**  
  Controla las operaciones sobre vehículos: agregar, cargar desde CSV, guardar, listar y arrendar.

- **VehiculoService**  
  Gestiona el almacenamiento de vehículos en memoria usando un `HashMap` sincronizado para concurrencia.

- **MenuController**  
  Maneja la interacción con el usuario, mostrando menús y delegando acciones a los controladores.

- **FileIOUtil**  
  Utilidad para leer y escribir vehículos en archivos CSV.

- **Modelos de Vehículo**  
  - `VehiculoPasajeros`: Vehículos para personas, con capacidad de pasajeros.  
  - `VehiculoCarga`: Vehículos para carga, con capacidad en kilos.

---

## 3. Flujo Básico de Uso

- **Inicio**  
  El usuario ve el menú principal y selecciona una opción.

- **Agregar Vehículo**  
  El usuario ingresa los datos del vehículo (pasajeros o carga).  
  El sistema valida y agrega al mapa de vehículos.

- **Listar Vehículos**  
  Se pueden listar por tipo o todos juntos.  
  La información se muestra en formato tabla alineada.

- **Arrendar Vehículo**  
  El usuario selecciona un vehículo disponible y define los días de arriendo.  
  El sistema actualiza el estado y muestra la boleta.

- **Persistencia**  
  Los vehículos se cargan y guardan en archivos CSV para mantener los datos entre ejecuciones.

---

## 4. Concurrencia y Seguridad

- El acceso al mapa de vehículos está sincronizado para evitar problemas en ambientes multi-hilo.
- El sistema valida patentes únicas y datos correctos antes de agregar vehículos.

---

## 5. Extensión y Mantenimiento

- El diseño modular permite agregar nuevos tipos de vehículos o funcionalidades fácilmente.
- El uso de interfaces y herencia facilita la reutilización