package io.github.somnifobia.customersupportapi.service;

import io.github.somnifobia.customersupportapi.dto.ChamadoRequestDTO;
import io.github.somnifobia.customersupportapi.dto.ChamadoResponseDTO;
import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import io.github.somnifobia.customersupportapi.model.Chamado;
import io.github.somnifobia.customersupportapi.model.Cliente;
import io.github.somnifobia.customersupportapi.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ClienteService clienteService;

    public ChamadoService(ChamadoRepository chamadoRepository, ClienteService clienteService) {
        this.chamadoRepository = chamadoRepository;
        this.clienteService = clienteService;
    }

    public ChamadoResponseDTO salvar(ChamadoRequestDTO dto) {
        Cliente cliente = clienteService.buscarEntidadePorId(dto.getClienteId());
        Chamado chamado = new Chamado();
        chamado.setTitulo(dto.getTitulo());
        chamado.setDescricao(dto.getDescricao());
        chamado.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusChamado.ABERTO);
        chamado.setCliente(cliente);
        return toDTO(chamadoRepository.save(chamado));
    }

    public List<ChamadoResponseDTO> listar(StatusChamado status, Long clienteId) {
        List<Chamado> chamados;
        if (status != null && clienteId != null) {
            chamados = chamadoRepository.findByStatusAndClienteId(status, clienteId);
        } else if (status != null) {
            chamados = chamadoRepository.findByStatus(status);
        } else if (clienteId != null) {
            chamados = chamadoRepository.findByClienteId(clienteId);
        } else {
            chamados = chamadoRepository.findAll();
        }
        return chamados.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ChamadoResponseDTO buscarPorId(Long id) {
        return toDTO(chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado com id: " + id)));
    }

    private ChamadoResponseDTO toDTO(Chamado c) {
        return new ChamadoResponseDTO(c.getId(), c.getTitulo(), c.getDescricao(), c.getStatus(), c.getDataAbertura(),
                c.getCliente().getId(), c.getCliente().getNome());
    }
}
