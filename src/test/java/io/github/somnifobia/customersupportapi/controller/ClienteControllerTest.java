package io.github.somnifobia.customersupportapi.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.somnifobia.customersupportapi.dto.ClienteRequestDTO;
import io.github.somnifobia.customersupportapi.dto.ClienteResponseDTO;
import io.github.somnifobia.customersupportapi.service.ClienteService;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void deveCriarClienteComSucesso() throws Exception {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setNome("Vinicius");
        request.setEmail("vinicius@email.com");

        ClienteResponseDTO response = new ClienteResponseDTO(
                1L, "Vinicius", "vinicius@email.com", null
        );

        when(clienteService.salvar(any())).thenReturn(response);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Vinicius"));
    }

    @Test
    void deveRetornarErroDe400QuandoNomeFaltando() throws Exception {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setEmail("vinicius@email.com");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos.nome").exists());
    }

    @Test
    void deveListarClientes() throws Exception {
        when(clienteService.listarTodos())
                .thenReturn(List.of(
                        new ClienteResponseDTO(1L, "Vinicius", "v@email.com", null)
                ));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Vinicius"));
    }
}