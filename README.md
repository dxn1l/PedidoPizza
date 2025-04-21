# 🍕 Sistema de Gestión de Pedidos de Pizza en Línea

Este proyecto implementa un sistema completo de gestión de pedidos de pizza utilizando Java. Incluye autenticación, gestión de pedidos (crear, leer, actualizar, eliminar, pagar), una interfaz de consola interactiva y simulación de una pasarela de pago.

---

##  Link al repositorio de GitHub

[https://github.com/maquina-turing/MaquinaTuring](https://github.com/dxn1l/PedidoPizza) 

---

## ⚙️ Tecnologías usadas

- Java 17

- Maven

- JDBC (con MySQL o H2 para pruebas)

- JUnit 5 para pruebas unitarias

- Mockito para pruebas con mocks

- ANSI Escape Codes para color en consola

---

## 🧩 Arquitectura del Proyecto

```

├── src
│   ├── main
│   │   ├── java
│   │   │   ├── app
│   │   │   │   ├── Authenticator.java
│   │   │   │   ├── MenuPizza.java
│   │   │   │   ├── OrderManager.java
│   │   │   │   ├── PaymentProcessor.java
│   │   │   │   ├── Pedido.java
│   │   │   │   ├── validacion.java
│   │   │   └── db
│   │   │       └── DataBaseManager.java
│   │   └── resources
│   │       └── application.properties
│   ├── test
│       ├── java
│       │   ├── AuthenticatorTest.java
│       │   ├── ValidacionTest.java
│       │   ├── PedidoTest.java
│       │   ├── PaymentProcessorTest.java
│       │   ├── MenuPizzaTest.java
│       │   ├── DataBaseManagerTest.java
│       │   └── OrderManagerTest.java
│       │   └── MainTest.java

```

---

## 🚀 Cómo ejecutar

- Clonar el repositorio:

```bash
  git clone https://github.com/usuario/pizza-system.git
```
```bash
  cd pizza-system
```

- Configurar base de datos (si usas MySQL):

- Verifica tu application.properties:

  - db.url=jdbc:mysql://localhost:3312/pizzeria
  - db.user=admin
  - db.password=admin

- O usa H2 para pruebas sin instalar nada.

- Compilar y ejecutar:

```bash
  mvn clean compile
  mvn exec:java -Dexec.mainClass="Main"
```
---

## 🧪 Ejecutar tests

- Para ejecutar las pruebas unitarias, asegúrate de tener Maven instalado y ejecuta el siguiente comando en la raíz del proyecto:

```bash
  mvn test
```


- Incluye tests para:

    - Validación de nombre

    - Autenticación

    - Pedido

    - Pasarela de pagos (simulada)

    - MenuPizza

    - Simulación de flujo en Main