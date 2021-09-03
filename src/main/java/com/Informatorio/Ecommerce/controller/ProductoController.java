package com.Informatorio.Ecommerce.controller;

import com.Informatorio.Ecommerce.domain.Producto;
import com.Informatorio.Ecommerce.domain.Usuario;
import com.Informatorio.Ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    //Alta de un producto
    @PostMapping(value = "/producto")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto){
        return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.CREATED);
    }

    //Lista de productos
    @GetMapping(value = "/producto")
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    //Detalle del producto
    @GetMapping(value = "/producto/{id}")
    public Producto detalleProducto(@PathVariable("id") Long id){
        return productoRepository.getById(id);
    }

    //Borrar producto
    @DeleteMapping(value = "/producto/{id}")
    public void eliminarProducto(@PathVariable("id") Long id){
        Producto prod = productoRepository.getById(id);
        productoRepository.delete(prod);
    }

    //Modificar datos del producto
    @PutMapping(value = "/producto/{id}")
    public Producto editarProducto(@PathVariable("id") Long id, Producto prod){
        Producto nuevo_prod = productoRepository.getById(id);
        nuevo_prod.setNombre(prod.getNombre());
        nuevo_prod.setDescripcion(prod.getDescripcion());
        nuevo_prod.setContenido(prod.getContenido());
        nuevo_prod.setPrecio(prod.getPrecio());
        nuevo_prod.setPublicado(prod.getPublicado());
        return productoRepository.save(nuevo_prod);
    }

    //Productos que contienen un nombre dado
    @GetMapping(value = "/producto/contieneNombre")
    public List<Producto> productosCon(@RequestParam String nombre){
        return productoRepository.findByNombreContaining(nombre);
    }

    //Productos que no fueron publicados
    @GetMapping(value = "producto/noPublicados")
    public List<Producto> noPublicados(){
        return productoRepository.finByPublicadoFalse();
    }

}
