package uni.edu.pe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Maquinaria {
    private int id_maquinaria;
    private String codigo;
    private String marca;
    private String modelo;
    private String descripcion;
    private int id_planta;
}
