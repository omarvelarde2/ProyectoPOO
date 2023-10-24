package uni.edu.pe.backend.service;

import uni.edu.pe.backend.dto.Maquinaria;
import uni.edu.pe.backend.dto.TopOp;
import uni.edu.pe.backend.dto.ReporteOperador;

import java.util.List;

public interface EndService {
    List<ReporteOperador> obtenerTurnosOperador();
    Maquinaria registrarMaquinaria(Maquinaria maquinaria);
    TopOp actualizarEstadoTurno(TopOp TopOp);
}
