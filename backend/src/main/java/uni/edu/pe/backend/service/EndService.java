package uni.edu.pe.backend.service;

import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.ObjUs;
import uni.edu.pe.backend.dto.Usuario;

import java.util.List;

public interface EndService {
    // Se definen los métodos en una interfaz de service para luego ser implementados
    List<Pedido> obtenerPedidos();
    Pedido registrarPedido(Pedido pedido);
    Objeto insertarObjeto(Objeto objeto);
    Usuario registrarUsuario(Usuario usuario);
    ObjUs actualizarPrecioObjeto(ObjUs objus);
    String eliminarPedido(int id_objeto);
    String eliminarObjeto(int id_objeto);
}
