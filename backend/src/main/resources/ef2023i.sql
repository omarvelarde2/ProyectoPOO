CREATE TABLE planta(
                       id_planta NUMERIC(4),
                       superficie NUMERIC(4),
                       proceso VARCHAR(50),
                       direccion VARCHAR(100),
                       PRIMARY KEY(id_planta)
);

CREATE TABLE operador(
                         id_operador NUMERIC(4),
                         dni CHAR(8),
                         nombre VARCHAR(50),
                         telefono NUMERIC(9),
                         PRIMARY KEY(id_operador)
);

CREATE TABLE maquinaria(
                           id_maquinaria NUMERIC(4),
                           codigo CHAR(8),
                           marca VARCHAR(50),
                           modelo VARCHAR(50),
                           descripcion VARCHAR(100),
                           id_planta NUMERIC(4),
                           PRIMARY KEY(id_maquinaria),
                           FOREIGN KEY(id_planta) REFERENCES planta(id_planta)
);

CREATE TABLE turnos_operacion(
                                 id_turno_operacion NUMERIC(4),
                                 turno CHAR(1),
                                 fecha_inicio VARCHAR(10),
                                 fecha_fin VARCHAR(10),
                                 estado CHAR(1),
                                 id_operador NUMERIC(4),
                                 id_maquinaria NUMERIC(4),
                                 PRIMARY KEY(id_turno_operacion),
                                 FOREIGN KEY(id_operador) REFERENCES operador(id_operador),
                                 FOREIGN KEY(id_maquinaria) REFERENCES maquinaria(id_maquinaria)
);

INSERT INTO planta VALUES(1, 3000, "Fabricacion", "Calle La Victoria 2025");
INSERT INTO planta VALUES(2, 1500, "Envase", "Calle General Sifuentes 2040");

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
