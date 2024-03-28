create table rol_cliente(
                            id_rol_cliente SERIAL primary KEY,
                            id_rol int,
                            id_user int,
                            FOREIGN KEY (id_rol) REFERENCES rol(id_rol),
                            FOREIGN KEY (id_user) REFERENCES cliente(id_cliente)
);