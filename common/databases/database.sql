DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente (
                                 id_cliente SERIAL PRIMARY key,
                                 dni VARCHAR(20) NOT NULL,
                                 nombres VARCHAR(100) NOT NULL,
                                 ape_paterno VARCHAR(100) NOT NULL,
                                 ape_materno VARCHAR(100) NOT NULL,
                                 estado INT NOT NULL,
                                 usua_crea VARCHAR(45),
                                 date_create TIMESTAMP,
                                 usua_modif VARCHAR(45),
                                 date_modif TIMESTAMP,
                                 usua_delet VARCHAR(45),
                                 date_delet TIMESTAMP,
                                 CONSTRAINT uq_cliente_dni UNIQUE (dni)
);

-- Crear tabla direccion
DROP TABLE IF EXISTS direccion;
CREATE TABLE direccion (
                           id_direccion SERIAL PRIMARY KEY,
                           id_cliente INT ,
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
                           date_delet TIMESTAMP,
                           CONSTRAINT uq_direccion_email UNIQUE (email),
                           FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL
);

DROP TABLE IF EXISTS rol;
CREATE TABLE rol (
                     id_rol SERIAL primary KEY,
                     nombre_rol VARCHAR(100),
                     estado INT NOT NULL,
                     usua_crea VARCHAR(45),
                     date_create TIMESTAMP,
                     usua_modif VARCHAR(45),
                     date_modif TIMESTAMP,
                     usua_delet VARCHAR(45),
                     date_delet TIMESTAMP,
                     CONSTRAINT uq_rol_nombre UNIQUE (nombre_rol)
);

drop table if exists rol_cliente;
create table rol_cliente(
                            id_rol_cliente SERIAL primary KEY,
                            id_rol int,
                            id_cliente int,
                            estado INT NOT NULL,
                            usua_crea VARCHAR(45),
                            date_create TIMESTAMP,
                            usua_modif VARCHAR(45),
                            date_modif TIMESTAMP,
                            usua_delet VARCHAR(45),
                            date_delet TIMESTAMP,
                            FOREIGN KEY (id_rol) REFERENCES rol(id_rol),
                            FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE usuario (
                         id_user SERIAL primary KEY,
                         id_cliente int,
                         usuario VARCHAR(20) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         estado INT NOT NULL,
                         usua_crea VARCHAR(45),
                         date_create TIMESTAMP,
                         usua_modif VARCHAR(45),
                         date_modif TIMESTAMP,
                         usua_delet VARCHAR(45),
                         date_delet TIMESTAMP,
                         CONSTRAINT uq_usuario_nombre UNIQUE (usuario),
                         FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL
);

-- Crear tabla pedido
DROP TABLE IF EXISTS pedido;
CREATE TABLE pedido (
                        id_pedido SERIAL PRIMARY KEY,
                        fecha_inicio_pedido TIMESTAMP,
                        estado INT NOT NULL,
                        usua_crea VARCHAR(45),
                        date_create TIMESTAMP,
                        usua_modif VARCHAR(45),
                        date_modif TIMESTAMP,
                        usua_delet VARCHAR(45),
                        date_delet TIMESTAMP,
                        id_cliente INT NOT NULL,
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL
);
DROP TABLE IF EXISTS hist_pedido;
CREATE TABLE hist_pedido (
                             id_hist_pedido SERIAL primary key,
                             id_cliente int,
                             id_pedido int,
                             fecha_inicio_pedido TIMESTAMP,
                             estado INT NOT NULL,
                             usua_crea VARCHAR(45),
                             date_create TIMESTAMP,
                             usua_modif VARCHAR(45),
                             date_modif TIMESTAMP,
                             usua_delet VARCHAR(45),
                             date_delet TIMESTAMP,
                             FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL,
                             FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE SET NULL
);


-- Crear tabla hist_prod_pedido
DROP TABLE IF EXISTS hist_prod_pedido;
CREATE TABLE hist_prod_pedido (
                                  id_prod_pedido SERIAL primary key,
                                  id_hist_pedido int,
                                  fecha_ingreso TIMESTAMP,
                                  estado INT NOT NULL,
                                  usua_crea VARCHAR(45),
                                  date_create TIMESTAMP,
                                  usua_modif VARCHAR(45),
                                  date_modif TIMESTAMP,
                                  usua_delet VARCHAR(45),
                                  date_delet TIMESTAMP,
                                  FOREIGN KEY (id_hist_pedido) REFERENCES hist_pedido(id_hist_pedido) ON DELETE SET NULL
);
-- Crear tabla producto_pedido (Tabla intermediaria)
DROP TABLE IF EXISTS producto_pedido;
CREATE TABLE prod_pedido (
                             id_pedido INT NOT NULL,
                             id_prod INT NOT NULL,
                             cantidad INT,
                             fecha_ingreso TIMESTAMP,
                             estado INT NOT NULL,
                             usua_crea VARCHAR(45),
                             date_create TIMESTAMP,
                             usua_modif VARCHAR(45),
                             date_modif TIMESTAMP,
                             usua_delet VARCHAR(45),
                             date_delet TIMESTAMP,
                             FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE SET NULL,
                             FOREIGN KEY (id_prod) REFERENCES producto(id_producto) ON DELETE SET NULL
);


-- Crear tabla envio
DROP TABLE IF EXISTS envio;
CREATE TABLE envio (
                       id_envio SERIAL,
                       id_pedido INT NOT NULL,
                       forma_pago VARCHAR(30),
                       fec_ini_ped TIMESTAMP,
                       fec_fin_ped TIMESTAMP,
                       fecha_registro TIMESTAMP,
                       estado INT NOT NULL,
                       usua_crea VARCHAR(45),
                       date_create TIMESTAMP,
                       usua_modif VARCHAR(45),
                       date_modif TIMESTAMP,
                       usua_delet VARCHAR(45),
                       date_delet TIMESTAMP,
                       FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE SET NULL
);

-- Crear tabla estado
DROP TABLE IF EXISTS estado;
CREATE TABLE estado (
                        id_estado SERIAL,
                        id_pedido INT NOT NULL,
                        est_inicial VARCHAR(20),
                        fec_tran TIMESTAMP,
                        fecha_ingreso TIMESTAMP,
                        estado INT NOT NULL,
                        usua_crea VARCHAR(45),
                        date_create TIMESTAMP,
                        usua_modif VARCHAR(45),
                        date_modif TIMESTAMP,
                        usua_delet VARCHAR(45),
                        date_delet TIMESTAMP,
                        FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE SET NULL
);

-- Crear tabla factura
DROP TABLE IF EXISTS factura;
CREATE TABLE factura (
                         id_factura SERIAL PRIMARY KEY,
                         id_cliente INT NOT NULL,
                         id_pedido INT NOT NULL,
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
                         date_delet TIMESTAMP,
                         FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL,
                         FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE SET NULL
);

-- Crear tabla producto
DROP TABLE IF EXISTS producto;
CREATE TABLE producto (
                          id_producto SERIAL PRIMARY KEY,
                          id_categoria INT NOT NULL,
                          id_caracteristicas INT NOT NULL,
                          desc_larga_prod VARCHAR(255),
                          desc_corta_prod VARCHAR(100),
                          empresa VARCHAR(50),
                          marca VARCHAR(30),
                          modelo VARCHAR(30),
                          fec_ini_vlg TIMESTAMP,
                          fec_fin_vlg TIMESTAMP,
                          estado INT NOT NULL,
                          usua_crea VARCHAR(45),
                          date_create TIMESTAMP,
                          usua_modif VARCHAR(45),
                          date_modif TIMESTAMP,
                          usua_delet VARCHAR(45),
                          date_delet TIMESTAMP,
                          FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE SET NULL,
                          FOREIGN KEY (id_caracteristicas) REFERENCES caracteristicas(id_caracteristicas) ON DELETE SET NULL
);

-- Crear tabla categoria
DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
                           id_categoria SERIAL PRIMARY KEY,
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
                                 id_caracteristicas SERIAL PRIMARY KEY,
                                 id_producto INT NOT NULL,
                                 fec_ini_vlg TIMESTAMP,
                                 fec_fin_vlg TIMESTAMP,
                                 estado INT NOT NULL,
                                 usua_crea VARCHAR(45),
                                 date_create TIMESTAMP,
                                 usua_modif VARCHAR(45),
                                 date_modif TIMESTAMP,
                                 usua_delet VARCHAR(45),
                                 date_delet TIMESTAMP,
                                 FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE SET NULL
);

-- Crear tabla precio
DROP TABLE IF EXISTS precio;
CREATE TABLE precio (
                        id_precio SERIAL,
                        id_producto INT NOT NULL,
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
                        date_delet TIMESTAMP,
                        FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE SET NULL
);

