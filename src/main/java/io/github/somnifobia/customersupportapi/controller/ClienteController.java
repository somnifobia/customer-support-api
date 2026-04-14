package io.github.somnifobia.customersupportapi.controller;

import io.github.somnifobia.customersupportapi.dto.ClienteRequestDTO;
import io.github.somnifobia.customersupportapi.dto.ClienteResponseDTO;
import io.github.somnifobia.customersupportapi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@RequestBody @Valid ClienteRequestDTO dto) {
        return clienteService.salvar(dto);
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }
}