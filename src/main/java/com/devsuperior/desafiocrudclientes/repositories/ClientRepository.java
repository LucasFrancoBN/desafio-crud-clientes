package com.devsuperior.desafiocrudclientes.repositories;

import com.devsuperior.desafiocrudclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
