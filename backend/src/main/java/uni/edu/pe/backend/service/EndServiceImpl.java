package uni.edu.pe.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.edu.pe.backend.dao.EndDao;
import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.ObjUs;
import uni.edu.pe.backend.dto.Usuario;

import java.util.List;

@Service
public class EndServiceImpl implements EndService{
    @Autowired
    // Se crea objeto de dao para llamar a la ejecución de los métodos e implementar el service
    private EndDao dao;
    // Se sobreescriben los métodos de service y se implementan llamando a la capa dao
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
    public Usuario registrarUsuario(Usuario usuario){ return dao.registrarUsuario(usuario); }
    @Override
    public ObjUs actualizarPrecioObjeto(ObjUs objus) {
        return dao.actualizarPrecioObjeto(objus);
    }
    @Override
    public String eliminarPedido(int id_objeto) { return dao.eliminarPedido(id_objeto); }
    @Override
    public String eliminarObjeto(int id_objeto) { return dao.eliminarObjeto(id_objeto); }
}
