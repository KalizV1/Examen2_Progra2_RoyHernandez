# Parqueo Público

Aplicación desarrollada en Java con interfaz gráfica para la gestión de un parqueo público.  
El sistema permite registrar el ingreso y salida de vehículos, calcular el monto a pagar según el tiempo de estancia y mantener un historial de registros, aplicando arquitectura por capas y persistencia en archivos.

---

## 🎯 Objetivo del Proyecto

Desarrollar una aplicación de escritorio en Java que implemente:
- Arquitectura por capas
- Persistencia en archivos `.txt`
- Interfaz gráfica con Swing
- Control de reglas de negocio
- Uso responsable de Inteligencia Artificial
- Versionamiento con GitHub

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java  
- **IDE:** NetBeans  
- **Build:** Apache Ant  
- **Interfaz Gráfica:** Swing (JFrame, JTable)  
- **Persistencia:** Archivos de texto (.txt)  
- **Control de versiones:** GitHub  

---

## 📌 Funcionalidades del Sistema

### ✔ Registro de Ingreso
- Captura de placa del vehículo
- Selección del tipo (Carro / Moto)
- Hora de ingreso automática
- Validaciones de datos obligatorios
- Control de unicidad (no permite ingresar una placa que ya esté activa)

### ✔ Registro de Salida
- Selección del vehículo desde la tabla de activos
- Hora de salida automática
- Cálculo del monto a pagar en función del tiempo (₡500 por hora o fracción)

### ✔ Visualización
- Tabla de vehículos activos
- Tabla de historial de vehículos

### ✔ Persistencia
- Almacenamiento de vehículos activos en `activos.txt`
- Almacenamiento del historial en `historial.txt`

---

## 🧠 Reglas de Negocio

- No se permite registrar el ingreso de un vehículo cuya placa ya esté activa.
- Todos los campos obligatorios deben validarse antes de registrar un ingreso o salida.
- El monto a pagar se calcula según la diferencia entre hora de ingreso y salida, aplicando la tarifa definida.

---

## 🧱 Arquitectura del Sistema

La aplicación está organizada siguiendo una **arquitectura por capas**:


