package uni.edu.pe.backend.dto.rest;

import lombok.Data;
import uni.edu.pe.backend.dto.ReporteOperador;

import java.util.List;

@Data
public class RespuestaReporteOperador {
    private List<ReporteOperador> reportes;
}
