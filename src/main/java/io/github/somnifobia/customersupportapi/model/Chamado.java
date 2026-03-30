package io.github.somnifobia.customersupportapi.model;

import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamados")
public class Chamado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusChamado status;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Chamado() {
    }

    public Chamado(Long id, String titulo, String descricao, StatusChamado status, LocalDateTime dataAbertura, Cliente cliente) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.cliente = cliente;
    }

    @PrePersist
    public void prePersist() {
        if (this.dataAbertura == null) {
            this.dataAbertura = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusChamado.ABERTO;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
