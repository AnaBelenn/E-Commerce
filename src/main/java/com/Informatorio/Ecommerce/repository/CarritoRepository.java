package com.Informatorio.Ecommerce.repository;

import com.Informatorio.Ecommerce.domain.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito getById(Long id);
    List<Carrito> findByEstadoTrue();
}
