package com.Informatorio.Ecommerce.repository;

import com.Informatorio.Ecommerce.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario getById(Long id);
    List<Usuario> getByCiudad(String ciudad);
    List<Usuario> getByDesdeFecha (Date desdeFecha);
}
