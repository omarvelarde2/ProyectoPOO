package uni.edu.pe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.backend.dto.Maquinaria;
import uni.edu.pe.backend.dto.TopOp;
import uni.edu.pe.backend.dto.rest.RespuestaReporteOperador;
import uni.edu.pe.backend.service.EndService;

@CrossOrigin(origins = {"*"})
@RestController
public class EndController {

    @Autowired
    private EndService service;

    @RequestMapping(
            value = "/obtenerTurnosOperador",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )

    public RespuestaReporteOperador obtenerTurnosOperador(){
        RespuestaReporteOperador rsps = new RespuestaReporteOperador();
        rsps.setReportes(service.obtenerTurnosOperador());
        return rsps;
    }

    @RequestMapping(
            value = "/registrarMaquinaria",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8"
    )
    public Maquinaria registrarMaquinaria(@RequestBody Maquinaria maquinaria) {
        return service.registrarMaquinaria(maquinaria);
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
