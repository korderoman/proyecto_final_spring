-- Crear tabla login_password
DROP TABLE IF EXISTS login_password;
CREATE TABLE login_password (
                                id_cliente SERIAL PRIMARY KEY,
                                usuario VARCHAR(20) NOT NULL,
                                tipo_cliente VARCHAR(50) NOT NULL,
                                password VARCHAR(255) NOT NULL,
                                estado INT NOT NULL,
                                usua_crea VARCHAR(45),
                                date_create TIMESTAMP,
                                usua_modif VARCHAR(45),
                                date_modif TIMESTAMP,
                                usua_delet VARCHAR(45),
                                date_delet TIMESTAMP
);

-- Crear tabla cliente_persona
DROP TABLE IF EXISTS cliente_persona;
CREATE TABLE cliente_persona (
                                 id_cliente SERIAL PRIMARY KEY,
                                 tipo_usuario VARCHAR(50) NOT NULL,
                                 dni VARCHAR(20) NOT NULL,
                                 nombre VARCHAR(100) NOT NULL,
                                 ape_paterno VARCHAR(100) NOT NULL,
                                 ape_materno VARCHAR(100) NOT NULL,
                                 estado INT NOT NULL,
                                 usua_crea VARCHAR(45),
                                 date_create TIMESTAMP,
                                 usua_modif VARCHAR(45),
                                 date_modif TIMESTAMP,
                                 usua_delet VARCHAR(45),
                                 date_delet TIMESTAMP
);

-- Crear tabla cliente_empresa
DROP TABLE IF EXISTS cliente_empresa;
CREATE TABLE cliente_empresa (
                                 id_cliente SERIAL PRIMARY KEY,
                                 ruc VARCHAR(20) NOT NULL,
                                 razon_social VARCHAR(255) NOT NULL,
                                 codigo_envio VARCHAR(50) NOT NULL,
                                 nombre_comercial VARCHAR(255) NOT NULL,
                                 fec_ini_vlg TIMESTAMP,
                                 fec_fin_vlg TIMESTAMP,
                                 estado INT NOT NULL,
                                 usua_crea VARCHAR(45),
                                 date_create TIMESTAMP,
                                 usua_modif VARCHAR(45),
                                 date_modif TIMESTAMP,
                                 usua_delet VARCHAR(45),
                                 date_delet TIMESTAMP
);

-- Crear tabla direccion
DROP TABLE IF EXISTS direccion;
CREATE TABLE direccion (
                           id_cliente SERIAL,
                           id_direccion SERIAL PRIMARY KEY,
                           direccion TEXT NOT NULL,
                           ciudad VARCHAR(100) NOT NULL,
                           provincia VARCHAR(100) NOT NULL,
                           calle VARCHAR(100),
                           numero VARCHAR(20),
                           piso VARCHAR(10),
                           codigo_postal VARCHAR(20),
                           telefono_cont VARCHAR(20),
                           email VARCHAR(255),
                           fec_ini_vlg TIMESTAMP,
                           fec_fin_vlg TIMESTAMP,
                           estado INT NOT NULL,
                           usua_crea VARCHAR(45),
                           date_create TIMESTAMP,
                           usua_modif VARCHAR(45),
                           date_modif TIMESTAMP,
                           usua_delet VARCHAR(45),
                           date_delet TIMESTAMP
);

-- Crear tabla hist_pedido
DROP TABLE IF EXISTS hist_pedido;
CREATE TABLE hist_pedido (
                             id_cliente SERIAL,
                             id_pedido SERIAL PRIMARY KEY,
                             fecha_inicio_pedido TIMESTAMP,
                             estado INT NOT NULL,
                             usua_crea VARCHAR(45),
                             date_create TIMESTAMP,
                             usua_modif VARCHAR(45),
                             date_modif TIMESTAMP,
                             usua_delet VARCHAR(45),
                             date_delet TIMESTAMP
);

-- Crear tabla hist_prod_pedido
DROP TABLE IF EXISTS hist_prod_pedido;
CREATE TABLE hist_prod_pedido (
                                  id_pedido SERIAL,
                                  id_prod SERIAL,
                                  fecha_ingreso TIMESTAMP,
                                  estado INT NOT NULL,
                                  usua_crea VARCHAR(45),
                                  date_create TIMESTAMP,
                                  usua_modif VARCHAR(45),
                                  date_modif TIMESTAMP,
                                  usua_delet VARCHAR(45),
                                  date_delet TIMESTAMP
);

-- Crear tabla pedido
DROP TABLE IF EXISTS pedido;
CREATE TABLE pedido (
                        id_pedido SERIAL PRIMARY KEY,
                        id_cliente INT,
                        id_empresa INT,
                        fecha_inicio_pedido TIMESTAMP,
                        estado INT NOT NULL,
                        usua_crea VARCHAR(45),
                        date_create TIMESTAMP,
                        usua_modif VARCHAR(45),
                        date_modif TIMESTAMP,
                        usua_delet VARCHAR(45),
                        date_delet TIMESTAMP
);

-- Crear tabla prod_pedido
DROP TABLE IF EXISTS prod_pedido;
CREATE TABLE prod_pedido (
                             id_pedido SERIAL,
                             id_prod SERIAL,
                             cantidad INT,
                             fecha_ingreso TIMESTAMP,
                             estado INT NOT NULL,
                             usua_crea VARCHAR(45),
                             date_create TIMESTAMP,
                             usua_modif VARCHAR(45),
                             date_modif TIMESTAMP,
                             usua_delet VARCHAR(45),
                             date_delet TIMESTAMP
);

-- Crear tabla envio
DROP TABLE IF EXISTS envio;
CREATE TABLE envio (
                       id_pedido SERIAL,
                       forma_pago VARCHAR(50),
                       estado INT NOT NULL,
                       usua_crea VARCHAR(45),
                       date_create TIMESTAMP,
                       usua_modif VARCHAR(45),
                       date_modif TIMESTAMP,
                       usua_delet VARCHAR(45),
                       date_delet TIMESTAMP
);

-- Crear tabla estado
DROP TABLE IF EXISTS estado;
CREATE TABLE estado (
                        id_pedido SERIAL,
                        est_inicial VARCHAR(20),
                        fec_tran TIMESTAMP,
                        fecha_ingreso TIMESTAMP,
                        estado INT NOT NULL,
                        usua_crea VARCHAR(45),
                        date_create TIMESTAMP,
                        usua_modif VARCHAR(45),
                        date_modif TIMESTAMP,
                        usua_delet VARCHAR(45),
                        date_delet TIMESTAMP
);

-- Crear tabla factura
DROP TABLE IF EXISTS factura;
CREATE TABLE factura (
                         id_factura SERIAL PRIMARY KEY,
                         id_cliente INT,
                         id_pedido INT,
                         num_factura VARCHAR(50),
                         cantidad INT,
                         igv DECIMAL(10, 2),
                         fecha_ingreso TIMESTAMP,
                         estado INT NOT NULL,
                         usua_crea VARCHAR(45),
                         date_create TIMESTAMP,
                         usua_modif VARCHAR(45),
                         date_modif TIMESTAMP,
                         usua_delet VARCHAR(45),
                         date_delet TIMESTAMP
);

-- Crear tabla producto
DROP TABLE IF EXISTS producto;
CREATE TABLE producto (
                          id_producto SERIAL PRIMARY KEY,
                          id_categoria INT,
                          desc_larga_prod TEXT,
                          desc_corta_prod VARCHAR(255),
                          empresa VARCHAR(100),
                          marca VARCHAR(100),
                          modelo VARCHAR(100),
                          fec_ini_vlg TIMESTAMP,
                          fec_fin_vlg TIMESTAMP,
                          estado INT NOT NULL,
                          usua_crea VARCHAR(45),
                          date_create TIMESTAMP,
                          usua_modif VARCHAR(45),
                          date_modif TIMESTAMP,
                          usua_delet VARCHAR(45),
                          date_delet TIMESTAMP
);

-- Crear tabla categoria
DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
                           id_codigo SERIAL PRIMARY KEY,
                           descripcion VARCHAR(255),
                           estado INT NOT NULL,
                           usua_crea VARCHAR(45),
                           date_create TIMESTAMP,
                           usua_modif VARCHAR(45),
                           date_modif TIMESTAMP,
                           usua_delet VARCHAR(45),
                           date_delet TIMESTAMP
);

-- Crear tabla caracteristicas
DROP TABLE IF EXISTS caracteristicas;
CREATE TABLE caracteristicas (
                                 id_producto SERIAL,
                                 grupo_1 VARCHAR(255),
                                 grupo_2 VARCHAR(255),
                                 fec_ini_vlg TIMESTAMP,
                                 fec_fin_vlg TIMESTAMP,
                                 estado INT NOT NULL,
                                 usua_crea VARCHAR(45),
                                 date_create TIMESTAMP,
                                 usua_modif VARCHAR(45),
                                 date_modif TIMESTAMP,
                                 usua_delet VARCHAR(45),
                                 date_delet TIMESTAMP
);

-- Crear tabla precio
DROP TABLE IF EXISTS precio;
CREATE TABLE precio (
                        id_producto SERIAL,
                        precio DECIMAL(10, 2),
                        oferta BOOLEAN,
                        fec_ini_vlg TIMESTAMP,
                        fec_fin_vlg TIMESTAMP,
                        estado INT NOT NULL,
                        usua_crea VARCHAR(45),
                        date_create TIMESTAMP,
                        usua_modif VARCHAR(45),
                        date_modif TIMESTAMP,
                        usua_delet VARCHAR(45),
                        date_delet TIMESTAMP
);

DROP TABLE IF EXISTS rol;
CREATE TABLE rol (
                     id_rol SERIAL,
                     nombre_rol VARCHAR(100),
                     estado INT NOT NULL,
                     usua_crea VARCHAR(45),
                     date_create TIMESTAMP,
                     usua_modif VARCHAR(45),
                     date_modif TIMESTAMP,
                     usua_delet VARCHAR(45),
                     date_delet TIMESTAMP
);

drop table if exists rol_cliente;
create table rol_cliente(
                            id_rol_cliente SERIAL,
                            id_rol int,
                            id_cliente int,
                            estado INT NOT NULL,
                            usua_crea VARCHAR(45),
                            date_create TIMESTAMP,
                            usua_modif VARCHAR(45),
                            date_modif TIMESTAMP,
                            usua_delet VARCHAR(45),
                            date_delet TIMESTAMP
)
