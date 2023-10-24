package uni.edu.pe.backend.dto.rest;

import lombok.Data;
import uni.edu.pe.backend.dto.Pedido;

import java.util.List;

@Data
public class RespuestaReportePedidos {
    private List<Pedido> reportes;
}
