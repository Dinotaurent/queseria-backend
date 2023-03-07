# queseria-backend
carrito de compras con arquitectura de microservicios, api-rest, comunicación con Feing, persistencia en  base de datos Mysql y Mongodb


Para ejecutar correctamente este proyecto se deben realizar los siguentes pasos:

1. Tener instalado los drivers de Mysql y MongoDb.
2. Crear la base de datos con el nombre "db_ms_registros" para que el ms-registros pueda crear la coleccion correctamente o modificar la informacion a gusto en el application.yml de dicho microservicio.
3. Crear el esquema "bd-queseria" en el localhost de Mysql o modificar a gusto en los microservicios ms-productos y ms-facturas.
4. Debido a que se esta utilizando Jakarta y no Javax es necesario modificar el tipo de dato del campo foto de la tabla producto debido a que este se crea automaticamente como un "tinyblob", modificar a "longblob" para evitar problemas al registra la foto en el front-end.
