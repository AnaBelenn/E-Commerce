package com.Informatorio.Ecommerce.repository;

import com.Informatorio.Ecommerce.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto getById(Long id);
    List<Producto> findByNombreContaining(String nombre);
    @Query
    Iterable<Producto> findByPublicadoFalse();
}
