package io.github.somnifobia.customersupportapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.somnifobia.customersupportapi.dto.ClienteRequestDTO;
import io.github.somnifobia.customersupportapi.dto.ClienteResponseDTO;
import io.github.somnifobia.customersupportapi.model.Cliente;
import io.github.somnifobia.customersupportapi.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveSalvarClienteComSucesso() {
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setNome("Vinicius Martins");
        dto.setEmail("vinicius@email.com");
        dto.setTelefone("48999999999");

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setId(1L);
        clienteSalvo.setNome("Vinicius Martins");
        clienteSalvo.setEmail("vinicius@email.com");
        clienteSalvo.setTelefone("48999999999");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteSalvo);

        ClienteResponseDTO resultado = clienteService.salvar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("Vinicius Martins", resultado.getNome());
        assertEquals("vinicius@email.com", resultado.getEmail());
        assertEquals("48999999999", resultado.getTelefone());
    }
}