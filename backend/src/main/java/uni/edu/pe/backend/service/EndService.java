package uni.edu.pe.backend.service;

import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.TopOp;

import java.util.List;

public interface EndService {
    List<Pedido> obtenerPedidos();
    Pedido registrarPedido(Pedido pedido);

    Objeto insertarObjeto(Objeto objeto);
    TopOp actualizarEstadoTurno(TopOp TopOp);
}
