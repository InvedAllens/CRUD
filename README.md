# Sistema de Altas, Bajas y Consultas en Aplicacion de escritorio (JAVA)

Crud version JAVA

![](https://img.shields.io/github/stars/rubaanxd/CRUD.svg) ![](https://img.shields.io/github/forks/rubaanxd/CRUD.svg) ![](https://img.shields.io/github/tag/rubaanxd/CRUD.svg) ![](https://img.shields.io/github/release/rubaanxd/CRUD.svg) ![](https://img.shields.io/github/issues/rubaanxd/CRUD.svg) ![](https://img.shields.io/bower/v/CRUD.svg)

### Vision
Crear un Sistema de Altas, Bajas y Consultas en Java con interfaz Grafica completamente funcional.

### Funciones
- Login y registro de Usuarios.
- El Menu Principal cambiara dependiendo del tipo de usuario logeado.
- En ALTAS debera que tener que poder hacer validaciones con el NS del equipo, que el ticket sea valido y se pueda subir un documento     PDF   via sftp u otro metodo.
- En BAJAS se tendra que poder borrar el registro en el servidor de MySQL asi como el documento via sftp u otro metodo.
- En CONSULTAS tendra la opcion de buscar el ticket o en dado caso el NS del Equipo.
  - Por TICKET se desplegara informacion del Equipo y Ticket en cuestion.
  - Por NS de Equipo se desplegara informacion del Equipo y en una Tabla se enlistaran los detalles principales de los tickets asignados    a ese equipo junto con un boton para poder visualizar el PDF asignado al ticket seleccionado.

### Lista por Hacer

- [x] Inicio
  - [x] Login
  - [x] Registro
- [x] Pagina Principal
  - [x] Altas
      - [x] Ticket
      - [x] Toner
  - [ ] Bajas
      - [ ] Ticket
      - [ ] Toner
  - [x] Consultas
      - [x] Ticket
      - [x] Equipo
