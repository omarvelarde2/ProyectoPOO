package uni.edu.pe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteOperador {
    private int id_operador;
    private String dni;
    private String nombre;
    private int telefono;
}
