package uni.edu.pe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.TopOp;
import uni.edu.pe.backend.dto.rest.RespuestaReportePedidos;
import uni.edu.pe.backend.service.EndService;

@CrossOrigin(origins = {"*"})
@RestController
public class EndController {

    @Autowired
    private EndService service;

    @RequestMapping(
            value = "/obtenerPedidos",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )

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
            value = "/actualizarEstadoTurno",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public TopOp actualizarEstadoTurno(@RequestBody TopOp TopOp) {
        return service.actualizarEstadoTurno(TopOp);

    }
}
