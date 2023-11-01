package uni.edu.pe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {
    private int id_usuario;
    private String nombre;
    private String tarjeta;
    private int saldo;
    private String telefono;
    private String direccion;
}
