DROP DATABASE IF EXISTS SAMPLEDB;
CREATE DATABASE SAMPLEDB;
USE SAMPLEDB;

DROP TABLE IF EXISTS  PRODUCTOS_USUARIOS;
DROP TABLE IF EXISTS  USUARIO;
DROP TABLE IF EXISTS  PRODUCTO;

CREATE TABLE USUARIO(
EMAIL VARCHAR(100) PRIMARY KEY,
PASSWORD VARCHAR (16),
NOMBRE VARCHAR(20),
APELLIDO VARCHAR (20),
TELEFONO VARCHAR(11)
);


CREATE TABLE PRODUCTO(
NOMBREP VARCHAR(30) PRIMARY KEY,
DESCR VARCHAR(1000),
FOTO VARCHAR(100)
);


CREATE TABLE PRODUCTOS_USUARIOS(
NOMBRE_USUARIO VARCHAR (100),
NOMBRE_PRODUCTO VARCHAR(30),
TIEMPO VARCHAR(3),
FOREIGN KEY (NOMBRE_USUARIO) REFERENCES USUARIO(EMAIL),
PRIMARY KEY(NOMBRE_USUARIO,NOMBRE_PRODUCTO),
FOREIGN KEY (NOMBRE_PRODUCTO) REFERENCES PRODUCTO(NOMBREP)
);

INSERT INTO USUARIO VALUES('luislaramonsalvo@gmail.com','123','Luis','Lara','3219251701');

INSERT INTO PRODUCTO(NOMBREP,DESCR,FOTO)  VALUES('Flowwe','Flowwe es una plataforma de análisis integral de datos. Su organización comenzará a disponer de información de gestión integrada de forma rápida, sencilla y segura. En pocos minutos con Flowwe podrá analizar sus indicadores de negocio utilizando tableros y reportes.','https://assertsolutions.com/wp-content/uploads/2020/05/flowwe-01-1-150x49.png');
INSERT INTO PRODUCTO(NOMBREP,DESCR,FOTO)  VALUES('Terin','Terin es una plataforma de comunicaciones para centralizar, controlar, auditar y enviar mensajes, notificaciones y alertas a través de e-mails, sms, push android/ios, telegram, whatsApp, llamadas telefónicas y otros canales. Con Terin podrá crear flujos de comunicaciones, diseñar plantillas de mensajes y de ser necesario ampliar las capacidades de comunicación “desarrollando” nuevos plugins auto-instalables.','https://assertsolutions.com/wp-content/uploads/2020/05/terin-01-80x70.png');
INSERT INTO PRODUCTO(NOMBREP,DESCR,FOTO)  VALUES('Consultorio360','C360 es una plataforma para profesionales independientes o establecimientos de salud, permite gestionar turnos o citas de forma rápida e intuitiva, llevando registro de profesionales/pacientes y si lo desea, registrar todos los datos relacionados con una atención o consulta médica. C360 le permite organizar su consultorio o clínica, de forma ágil y segura.','https://assertsolutions.com/wp-content/uploads/2020/05/consultorio360-180x47.png');
INSERT INTO PRODUCTO(NOMBREP,DESCR,FOTO)  VALUES('E-Bike','Plataforma para la gestión de la reserva y utilización de bicicletas y monopatines eléctricos. Gestión de turnos, retiros y devoluciones, accesorios (cascos, chalecos).','https://assertsolutions.com/wp-content/uploads/2020/05/ebike-01-85x56.png');
INSERT INTO PRODUCTO(NOMBREP,DESCR,FOTO)  VALUES('Factura Electrónica','Sistema de Facturación Electrónica con integración online con AFIP, Contratos, Valorización de consumos y Portal para Clientes. La plataforma cuenta con API para automatización de comprobantes.','https://assertsolutions.com/wp-content/uploads/2020/05/factura-electronica-01-48x60.png');

INSERT INTO PRODUCTOS_USUARIOS(NOMBRE_USUARIO, NOMBRE_PRODUCTO, TIEMPO) VALUES ('luislaramonsalvo@gmail.com','Flowwe','1');
