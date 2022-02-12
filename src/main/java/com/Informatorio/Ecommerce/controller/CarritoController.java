package com.Informatorio.Ecommerce.controller;

import com.Informatorio.Ecommerce.domain.*;
import com.Informatorio.Ecommerce.repository.CarritoRepository;
import com.Informatorio.Ecommerce.repository.ProductoRepository;
import com.Informatorio.Ecommerce.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarritoController {

    private CarritoRepository carritoRepository;
    private ProductoRepository productoRepository;
    private UsuarioRepository usuarioRepository;

    public CarritoController(CarritoRepository carritoRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Creacion de un carrito
    @PostMapping(value = "/usuario/{idU}/carrito")
    public ResponseEntity<?> crearCarrito(@PathVariable("idU") Long idU, @Valid @RequestBody Carrito carrito) {
        Usuario user = usuarioRepository.getById(idU);
        for (Carrito detalle:user.getCarritos()) {
            if (detalle.isEstado()) {
                detalle.setEstado(false);
            }
        }
        carrito.setUsuario(user);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    //Lista de carritos
    @GetMapping(value = "/carrito")
    public List<Carrito> listarCarritos(){
        return carritoRepository.findAll();
    }

    //Detalle del carrito
    @GetMapping(value = "/carrito/{idC}")
    public Carrito detalleCarrito(@PathVariable("idC") Long id){
        return carritoRepository.getById(id);
    }


    //Eliminar un carrito
    @DeleteMapping(value = "/carrito/{idC}")
    public void borrarCarrito(@PathVariable("idC") Long idC){
        carritoRepository.deleteById(idC);
    }


    //Modifico un carrito agregando/borrando lineasCarrito
    //-> Agrego Linea de producto al carrito
    @PutMapping(value = "/carrito/{idC}/producto/{idP}")
    public ResponseEntity<?> agregarProducto(@PathVariable("idC") Long idC,
                                             @PathVariable("idP") Long idP,
                                             @RequestParam int cantidad){
        Carrito carrito = carritoRepository.getById(idC);
        Producto producto = productoRepository.getById(idP);
        Linea_carrito linea_carrito = new Linea_carrito();
        linea_carrito.setCarrito(carrito);
        linea_carrito.setProducto(producto);
        linea_carrito.setCantidad(cantidad);
        carrito.agregarLineaCarrito(linea_carrito);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    //-> Elimino una Linea del carrito
    @DeleteMapping(value = "/carrito/{idC}/linea/{idL}")
    public ResponseEntity<?> eliminarLinea(@PathVariable("idC") Long idC,
                                              @PathVariable("idL") Long idL){
        Carrito carrito = carritoRepository.getById(idC);
        List<Linea_carrito> lineasCarrito = carrito.getLineasCarrito();
        for (Linea_carrito linea : lineasCarrito) {
            if (linea.getIdL().equals(idL)) {
                carrito.borrarLineaCarrito(linea);
            }
        }
        return null;
    }

    //Obtener todos los carritos ACTIVOS
    @GetMapping(value = "/carrito/esActivo")
    public List<Carrito> carritosActivos(){
        return carritoRepository.findByEstadoTrue();
    }


    //CORREGIR - Checkout Carrito -> devolver detalle de la ORDEN
    @GetMapping(value = "/carrito/{idC}")
    public Carrito checkout(@PathVariable("idC") Long idC) {
        Carrito carrito = carritoRepository.getById(idC);
        if (carrito.isEstado() && (carrito.getLineasCarrito().size()) >= 1) {
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            return detalleCarrito(idC);
        }
        return null;
    }


}
