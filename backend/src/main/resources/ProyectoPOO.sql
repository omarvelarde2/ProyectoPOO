CREATE TABLE usuario(
                       id_usuario NUMERIC(6),
                       dni CHAR(8),
                       nombre VARCHAR(50),
                       saldo NUMERIC(5),
                       telefono NUMERIC(9),
                       direccion VARCHAR(100),
                       PRIMARY KEY(id_usuario)
);

CREATE TABLE objeto(
                        id_objeto NUMERIC(6),
                        nombre VARCHAR(50),
                        descripcion VARCHAR(100),
                        precio NUMERIC(5),
                        PRIMARY KEY(id_objeto)
);

CREATE TABLE pedido(
                        id_pedido NUMERIC(6),
                        id_usuario NUMERIC(6),
                        id_objeto NUMERIC(6),
                        fecha VARCHAR(10),
                        PRIMARY KEY(id_pedido),
                        FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
                        FOREIGN KEY(id_objeto) REFERENCES objeto(id_objeto)
);



INSERT INTO usuario VALUES(000001, "73859473", "Juan Lopez", 0, 938274910);

INSERT INTO operador VALUES(1, "44162312", "Juan Chumpitaz", 99635953);
INSERT INTO operador VALUES(2, "44162315", "Juan Chumpitaz", 99635942);
INSERT INTO operador VALUES(3, "44162324", "Juan Chumpitaz", 99633458);
