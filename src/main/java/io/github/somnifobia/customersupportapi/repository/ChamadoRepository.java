package io.github.somnifobia.customersupportapi.repository;

import io.github.somnifobia.customersupportapi.enums.StatusChamado;
import io.github.somnifobia.customersupportapi.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByStatus(StatusChamado status);
    List<Chamado> findByClienteId(Long clienteId);
    List<Chamado> findByStatusAndClienteId(StatusChamado status, Long clienteId);
}
