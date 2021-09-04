package com.Informatorio.Ecommerce.controller;

import com.Informatorio.Ecommerce.domain.Carrito;
import com.Informatorio.Ecommerce.domain.Orden;
import com.Informatorio.Ecommerce.domain.Usuario;
import com.Informatorio.Ecommerce.repository.CarritoRepository;
import com.Informatorio.Ecommerce.repository.OrdenRepository;
import com.Informatorio.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping(value = "/orden")
    public List<Orden> listarOrdenes(){
        return ordenRepository.findAll();
    }

    @GetMapping(value = "/orden/{idO}")
    public Orden verOrden(@PathVariable("idO") Long idO){
        return ordenRepository.getById(idO);
    }

    @PostMapping(value = "/orden/{idC}")
    public Orden crearOrden(@PathVariable("idC") Long idC,
                            @RequestBody Orden orden){
        Carrito carrito = carritoRepository.getById(idC);
        if (carrito.isEstado()){
            orden.setCarrito_id(idC);
            orden.setFechaCreacion(orden.getFechaCreacion());
            orden.setObservacion(orden.getObservacion());
            orden.setLineaCarritos(carrito.getLineasCarrito());
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            return ordenRepository.save(orden);
        }
        return null;
    }

    @DeleteMapping(value = "/orden/{idO}")
    public void borrarOrden(@PathVariable("idO") Long idO) {
        Orden orden = ordenRepository.getById(idO);
        ordenRepository.delete(orden);
    }

    @GetMapping(value = "/usuario/{idU}/orden")
    public List<Orden> ordenesDelUsuario(@PathVariable("idU") Long idU){
        Usuario usuario = usuarioRepository.getById(idU);
        return ordenRepository.findByUsuario(usuario);
    }
}
