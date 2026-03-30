package io.github.somnifobia.customersupportapi.service;

import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import io.github.somnifobia.customersupportapi.model.Chamado;
import io.github.somnifobia.customersupportapi.model.Cliente;
import io.github.somnifobia.customersupportapi.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ClienteService clienteService;

    public ChamadoService(ChamadoRepository chamadoRepository, ClienteService clienteService) {
        this.chamadoRepository = chamadoRepository;
        this.clienteService = clienteService;
    }

    public Chamado salvar(Chamado chamado) {
        Long clientId = chamado.getCliente().getId();
        Cliente cliente = clienteService.buscarPorId(clientId);
        chamado.setCliente(cliente);
        return chamadoRepository.save(chamado);
    }

    public List<Chamado> listar(StatusChamado status, Long clienteId) {
        if (status != null && clienteId != null) {
            return chamadoRepository.findByStatusAndClienteId(status, clienteId);
        }
        if (status != null) {
            return chamadoRepository.findByStatus(status);
        }
        if (clienteId != null) {
            return chamadoRepository.findByClienteId(clienteId);
        }
        return chamadoRepository.findAll();
    }

    public Chamado buscarPorId(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado com id: " + id));
    }
}
