package io.github.somnifobia.customersupportapi.controller;

import io.github.somnifobia.customersupportapi.dto.ChamadoRequestDTO;
import io.github.somnifobia.customersupportapi.dto.ChamadoResponseDTO;
import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import io.github.somnifobia.customersupportapi.service.ChamadoService;
import jakarta.validation.Valid;
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
    public ChamadoResponseDTO criar(@RequestBody @Valid ChamadoRequestDTO dto) {
        return chamadoService.salvar(dto);
    }

    @GetMapping
    public List<ChamadoResponseDTO> listar(
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) Long clienteId) {
        return chamadoService.listar(status, clienteId);
    }

    @GetMapping("/{id}")
    public ChamadoResponseDTO buscarPorId(@PathVariable Long id) {
        return chamadoService.buscarPorId(id);
    }
}