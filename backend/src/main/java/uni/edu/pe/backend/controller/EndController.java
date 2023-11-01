package uni.edu.pe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.ObjUs;
import uni.edu.pe.backend.dto.Usuario;
import uni.edu.pe.backend.dto.rest.RespuestaReportePedidos;
import uni.edu.pe.backend.service.EndService;

@CrossOrigin(origins = {"*"})
@RestController
public class EndController {
    // Se habilita la auto inyección de dependencias (xml)
    @Autowired
    // Se crea objeto de service para usar sus métodos
    private EndService service;

    // Se solicita mapping para cada método y poder probarlos (siempre en POST)
    @RequestMapping(
            value = "/obtenerPedidos",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )

    // Se llama a los métodos de la capa service, lo mismo para cada uno
    public RespuestaReportePedidos obtenerPedidos(){
        RespuestaReportePedidos rsps = new RespuestaReportePedidos();
        rsps.setReportes(service.obtenerPedidos());
        return rsps;
    }

    @RequestMapping(
            value = "/registrarPedido",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public Pedido registrarPedido(@RequestBody Pedido pedido) {
        return service.registrarPedido(pedido);
    }

    @RequestMapping(
            value = "/insertarObjeto",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public Objeto insertarObjeto(@RequestBody Objeto objeto) {
        return service.insertarObjeto(objeto);
    }

    @RequestMapping(
            value = "/registrarUsuario",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return service.registrarUsuario(usuario);
    }

    @RequestMapping(
            value = "/actualizarPrecioObjeto",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public ObjUs actualizarPrecioObjeto(@RequestBody ObjUs objus) {
        return service.actualizarPrecioObjeto(objus);
    }

    @RequestMapping(
            value = "/eliminarPedido",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public String eliminarPedido(@RequestBody int id_objeto) { return service.eliminarPedido(id_objeto); }

    @RequestMapping(
            value = "/eliminarObjeto",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public String eliminarObjeto(@RequestBody int id_objeto) { return service.eliminarObjeto(id_objeto); }
}


