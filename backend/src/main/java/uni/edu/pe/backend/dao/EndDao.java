package uni.edu.pe.backend.dao;

import uni.edu.pe.backend.dto.Maquinaria;
import uni.edu.pe.backend.dto.TopOp;
import uni.edu.pe.backend.dto.ReporteOperador;

import java.util.List;

public interface EndDao {
    List<ReporteOperador> obtenerTurnosOperador();
    Maquinaria insertarMaquinaria(Maquinaria maquinaria);
    TopOp actualizarEstadoTurno(TopOp TopOp);
}
