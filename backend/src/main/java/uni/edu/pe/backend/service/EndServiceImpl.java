package uni.edu.pe.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.edu.pe.backend.dao.EndDao;
import uni.edu.pe.backend.dto.Maquinaria;
import uni.edu.pe.backend.dto.TopOp;
import uni.edu.pe.backend.dto.ReporteOperador;

import java.util.List;

@Service
public class EndServiceImpl implements EndService{

    @Autowired
    private EndDao dao;
    @Override
    public List<ReporteOperador> obtenerTurnosOperador() {
        return dao.obtenerTurnosOperador();
    }
    @Override
    public Maquinaria registrarMaquinaria(Maquinaria maquinaria) {
        return dao.insertarMaquinaria(maquinaria);
    }

    @Override
    public TopOp actualizarEstadoTurno(TopOp TopOp) {
        return dao.actualizarEstadoTurno(TopOp);
    }

}
