-- -----------------------------------------------------
-- CREATE DATABASE 'hpawsbbdd'
-- -----------------------------------------------------
create database IF NOT EXISTS hpawsbbdd DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

use hpawsbbdd;

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ROLES'
-- -----------------------------------------------------
CREATE TABLE ROLES
(
IDROL INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMBRE VARCHAR(30) NOT NULL
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'PROVINCIAS'
-- -----------------------------------------------------
CREATE TABLE PROVINCIAS
(
IDPROVINCIA INT NOT NULL PRIMARY KEY,
	PROVINCIA VARCHAR (30) NOT NULL
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'MUNICIPIOS'
-- -----------------------------------------------------
CREATE TABLE MUNICIPIOS
(
IDMUNICIPIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDPROVINCIA INT NOT NULL,
CODMUNICIPIO INT NOT NULL,
DC INT NOT NULL,
	MUNICIPIO VARCHAR (100) NOT NULL,
	
FOREIGN KEY(IDPROVINCIA) REFERENCES PROVINCIAS(IDPROVINCIA)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'USUARIOS'
-- -----------------------------------------------------
CREATE TABLE USUARIOS
(
IDUSUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDROL INT NOT NULL,
IDMUNICIPIO INT,
	enabled BOOLEAN NOT NULL DEFAULT false,
	NOMBRE VARCHAR(50),
	APELLIDOS VARCHAR(150) NOT NULL,
	DNI VARCHAR(10) NOT NULL,
	DIRECCION VARCHAR (200),
	TELEFONO INT,
	EMAIL VARCHAR(150) NOT NULL UNIQUE,
	EMAIL_NORMALIZADO VARCHAR(150) NOT NULL UNIQUE,
	PASSWORD VARCHAR(60) NOT NULL,
	FECHA_ALTA DATE,
	FECHA_enabled DATE,

FOREIGN KEY(IDROL) REFERENCES ROLES(IDROL),
FOREIGN KEY(IDMUNICIPIO) REFERENCES MUNICIPIOS(IDMUNICIPIO)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ESTADOSPROTECTORAS'
-- -----------------------------------------------------
CREATE TABLE ESTADOSPROTECTORAS
(
IDESTADOPROTECTORA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	ESTADO VARCHAR(20) NOT NULL
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'PROTECTORAS'
-- -----------------------------------------------------
CREATE TABLE PROTECTORAS
(
IDPROTECTORA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDUSUARIO INT NULL,
IDESTADOPROTECTORA INT NOT NULL DEFAULT 3,
IDMUNICIPIO INT,
	NOMBRE VARCHAR (150) NOT NULL,
	DIRECCION VARCHAR (250),
	URL_LOGO VARCHAR (2000),
	DESCRIPCION VARCHAR (1000),
	EMAIL VARCHAR(150) NOT NULL,
	TELEFONO INT,

FOREIGN KEY(IDUSUARIO) REFERENCES USUARIOS(IDUSUARIO),
FOREIGN KEY(IDMUNICIPIO) REFERENCES MUNICIPIOS(IDMUNICIPIO),
FOREIGN KEY(IDESTADOPROTECTORA) REFERENCES ESTADOSPROTECTORAS(IDESTADOPROTECTORA)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'PREGUNTAS_ADOPTANTE'
-- -----------------------------------------------------
CREATE TABLE PREGUNTAS_ADOPTANTES
(
IDPREGUNTA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	PREGUNTA VARCHAR(1000) NOT NULL
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'RESPUESTAS_ADOPTANTE'
-- -----------------------------------------------------
CREATE TABLE RESPUESTAS_ADOPTANTE
(
IDRESPUESTA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDPREGUNTA INT NOT NULL,
IDUSUARIO INT NOT NULL,
	RESPUESTA VARCHAR (1000) NOT NULL,

FOREIGN KEY(IDPREGUNTA) REFERENCES PREGUNTAS_ADOPTANTES(IDPREGUNTA),
FOREIGN KEY(IDUSUARIO) REFERENCES USUARIOS(IDUSUARIO)

);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ESPECIE'
-- -----------------------------------------------------
CREATE TABLE ESPECIE (
    IDESPECIE INT AUTO_INCREMENT PRIMARY KEY,
    ESPECIE VARCHAR(50)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'RAZA'
-- -----------------------------------------------------
CREATE TABLE RAZA (
    IDRAZA INT AUTO_INCREMENT PRIMARY KEY,
    IDESPECIE INT,
    RAZA VARCHAR(50),
    FOREIGN KEY (IDESPECIE) REFERENCES ESPECIE(IDESPECIE)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'SEXO'
-- -----------------------------------------------------
CREATE TABLE SEXO (
    IDSEXO INT AUTO_INCREMENT PRIMARY KEY,
    SEXO VARCHAR(10)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'TAMAÑO'
-- -----------------------------------------------------
CREATE TABLE TAMANO (
    IDTAMANO INT AUTO_INCREMENT PRIMARY KEY,
    TAMANO VARCHAR(20)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ANIMALES'
-- -----------------------------------------------------
CREATE TABLE ANIMALES
(
IDANIMAL INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDPROTECTORA INT NOT NULL,
IDMUNICIPIO INT,
IDRAZA INT,
IDSEXO INT,
IDTAMANO INT,
	ENVIO BOOLEAN,
	ENABLED BOOLEAN NOT NULL DEFAULT false,
	NOMBRE VARCHAR (20) NOT NULL,
	FECHA_NACIMIENTO DATE, 
	FECHA_ALTA DATE,
	FECHA_enabled DATE, 
	DESCRIPCION VARCHAR (1000),
FOREIGN KEY(IDPROTECTORA) REFERENCES PROTECTORAS(IDPROTECTORA),
FOREIGN KEY(IDMUNICIPIO) REFERENCES MUNICIPIOS(IDMUNICIPIO),
FOREIGN KEY(IDRAZA) REFERENCES RAZA(IDRAZA),
FOREIGN KEY(IDSEXO) REFERENCES SEXO(IDSEXO),
FOREIGN KEY(IDTAMANO) REFERENCES TAMANO(IDTAMANO)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ESTADOSADOPCIONES'
-- -----------------------------------------------------
CREATE TABLE ESTADOSADOPCIONES
(
IDESTADOADOPCION INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	ESTADO VARCHAR(20) NOT NULL
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ADOPCIONES'
-- -----------------------------------------------------
CREATE TABLE ADOPCIONES
(
IDADOPCION INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDUSUARIO INT NOT NULL,
IDPROTECTORA INT NOT NULL,
IDANIMAL INT NOT NULL,
IDESTADOADOPCION INT NOT NULL DEFAULT 1 ,
	FECHA_ADOPCION DATE,
	 
FOREIGN KEY(IDUSUARIO) REFERENCES USUARIOS(IDUSUARIO),
FOREIGN KEY (IDPROTECTORA) REFERENCES PROTECTORAS (IDPROTECTORA),
FOREIGN KEY (IDANIMAL)REFERENCES ANIMALES(IDANIMAL),
FOREIGN KEY (IDESTADOADOPCION)REFERENCES ESTADOSADOPCIONES(IDESTADOADOPCION)
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'MULTIMEDIAS'
-- -----------------------------------------------------
CREATE TABLE MULTIMEDIAS
(
IDMULTIMEDIA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDANIMAL INT NOT NULL,
	ENLACE LONGTEXT NOT NULL,
	
FOREIGN KEY(IDANIMAL) REFERENCES ANIMALES(IDANIMAL)
);
/*
-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'TIPOS_ATRIBUTOS
-- -----------------------------------------------------
CREATE TABLE TIPOS_ATRIBUTOS
(
IDTIPO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	ESPECIE VARCHAR (20) NOT NULL,
	TIPO VARCHAR (20) NOT NULL
	
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'VALORES_ATRIBUTOS'
-- -----------------------------------------------------
CREATE TABLE VALORES_ATRIBUTOS
(
IDVALOR INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDTIPO INT NOT NULL,
	VALOR VARCHAR (20) NOT NULL,

FOREIGN KEY (IDTIPO) REFERENCES TIPOS_ATRIBUTOS (IDTIPO)	
);

-- -----------------------------------------------------
-- Table 'hpawsbbdd'.'ATRIBUTOS_ANIMALES'
-- -----------------------------------------------------
CREATE TABLE ATRIBUTOS_ANIMALES
(

#IDATRIBUTOANIMAL INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
IDANIMAL INT NOT NULL,
IDVALOR INT NOT NULL,
PRIMARY KEY (IDANIMAL, IDVALOR),

FOREIGN KEY (IDANIMAL) REFERENCES ANIMALES (IDANIMAL),
FOREIGN KEY (IDVALOR) REFERENCES VALORES_ATRIBUTOS (IDVALOR)	
);
*/
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREAR USUARIO Y DARLE PRIVILEGIO SOBRE LA BASE DE DATOS
-- (EJECUTAR DESDE ROOT)
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------

 CREATE USER uhappypaws IDENTIFIED BY 'p9RYGQMETi%u{6c';
 grant all privileges on hpawsbbdd.* to uhappypaws; 
 FLUSH PRIVILEGES;
 
 
 