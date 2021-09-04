package com.Informatorio.Ecommerce.repository;

import com.Informatorio.Ecommerce.domain.Orden;
import com.Informatorio.Ecommerce.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Orden getById(Long id);
    List<Orden> findByUsuario(Usuario usuario);
}
