package uni.edu.pe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Objeto {
    private int id_objeto;
    private int id_vendedor;
    private String nombre;
    private String descripcion;
    private double precio;
}
