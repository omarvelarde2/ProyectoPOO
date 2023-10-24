package uni.edu.pe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopOp {
    private int id_operador;
    private String dni;
    private String nombre;
    private int telefono;
    private int id_turno_operacion;
    private String turno;
    private String estado;
    private int id_maquinaria;
}
