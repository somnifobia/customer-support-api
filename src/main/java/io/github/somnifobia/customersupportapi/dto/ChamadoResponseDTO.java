package io.github.somnifobia.customersupportapi.dto;

import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import java.time.LocalDateTime;


public class ChamadoResponseDTO {
    
    private Long id;
    private String titulo;
    private String descricao;
    private StatusChamado status;
    private LocalDateTime dataAbertura;
    private Long clienteId;
    private String clienteNome;

    public ChamadoResponseDTO(Long id, String titulo, String descricao, StatusChamado status, LocalDateTime dataAbertura, Long clienteId, String clienteNome) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public Long getClienteId() { 
        return clienteId; 
    }

    public String getClienteNome() {
        return clienteNome;
    }
}
