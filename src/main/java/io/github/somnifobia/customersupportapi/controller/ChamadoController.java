package io.github.somnifobia.customersupportapi.controller;

import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import io.github.somnifobia.customersupportapi.model.Chamado;
import io.github.somnifobia.customersupportapi.service.ChamadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chamado criar(@RequestBody Chamado chamado) {
        return chamadoService.salvar(chamado);
    }

    @GetMapping
    public List<Chamado> listar(
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) Long clienteId
    )   {
        return chamadoService.listar(status, clienteId);
    }

    @GetMapping("/{id}")
    public Chamado buscarPorId(@PathVariable Long id) {
        return chamadoService.buscarPorId(id);
    }
}
