package io.github.somnifobia.customersupportapi.repository;

import io.github.somnifobia.customersupportapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
