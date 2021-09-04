package com.Informatorio.Ecommerce.repository;

import com.Informatorio.Ecommerce.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario getById(Long id);
    List<Usuario> getByCiudad(String ciudad);
    Optional<Usuario> getByFechaCreacion (LocalDate desde);
}
