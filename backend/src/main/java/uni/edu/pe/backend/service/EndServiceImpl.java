package uni.edu.pe.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.edu.pe.backend.dao.EndDao;
import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.TopOp;

import java.util.List;

@Service
public class EndServiceImpl implements EndService{

    @Autowired
    private EndDao dao;
    @Override
    public List<Pedido> obtenerPedidos() {
        return dao.obtenerPedidos();
    }
    @Override
    public Pedido registrarPedido(Pedido pedido) {
        return dao.registrarPedido(pedido);
    }

    @Override
    public Objeto insertarObjeto(Objeto objeto) { return dao.insertarObjeto(objeto); }

    @Override
    public TopOp actualizarEstadoTurno(TopOp TopOp) {
        return dao.actualizarEstadoTurno(TopOp);
    }

}
