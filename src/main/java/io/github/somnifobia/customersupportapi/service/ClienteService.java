package io.github.somnifobia.customersupportapi.service;

import io.github.somnifobia.customersupportapi.dto.ClienteRequestDTO;
import io.github.somnifobia.customersupportapi.dto.ClienteResponseDTO;
import io.github.somnifobia.customersupportapi.model.Cliente;
import io.github.somnifobia.customersupportapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        Cliente salvo = clienteRepository.save(cliente);
        return toDTO(salvo);
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
        return toDTO(cliente);
    }

    public Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    private ClienteResponseDTO toDTO(Cliente c) {
        return new ClienteResponseDTO(c.getId(), c.getNome(), c.getEmail(), c.getTelefone());
    }
}
