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

-- Pregunta 1 --
INSERT INTO maquinaria VALUES(1, "MAQ10001", "Sommetrade", "Llenadoras", "Liquidos y semiliquidos viscososos...", 1);
INSERT INTO maquinaria VALUES(2, "MAQ10002", "Renault", "somme marina", "cerradoras nueva...", 1);
INSERT INTO maquinaria VALUES(3, "MAQ10002", "Ezequerra", "sommeq6", "cerradoras cilindrico...", 2);
INSERT INTO maquinaria VALUES(4, "MAQ10002", "Ezequerra", "e-320", "cerradoras cilindrico...", 1);
INSERT INTO maquinaria VALUES(5, "MAQ10002", "Sommetrade", "grp-330", "grupo llenado-cerrado...", 2);
-- Pregunta 1 --

INSERT INTO turnos_operacion VALUES(1, "M", "01-06-2023", "30-07-2023", "A", 1, 1);

-- Pregunta 2 --
UPDATE turnos_operacion top AS top
    INNER JOIN operador AS op
ON op.id_operador = top.id_operador
    SET top.estado = "A"
WHERE op.dni LIKE "4416231_"
-- Pregunta 2 --

-- Pregunta 3 --
SELECT *
FROM operador op
INNER JOIN turnos_operacion top
ON op.id_operador = top.id_operador
INNER JOIN maquinaria maq
ON maq.id_maquinaria = top.id_maquinaria
