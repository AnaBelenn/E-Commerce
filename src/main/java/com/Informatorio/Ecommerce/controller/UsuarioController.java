package com.Informatorio.Ecommerce.controller;

import com.Informatorio.Ecommerce.domain.Usuario;
import com.Informatorio.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Alta de usuario
    @PostMapping(value = "/usuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    //Lista de usuarios
    @GetMapping(value = "/usuario")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    //Detalle de usuario
    @GetMapping(value = "/usuario/{id}")
    public Usuario detalleUsuario(@PathVariable("id") Long id){
        return usuarioRepository.getById(id);
    }

    //Borrar usuario
    @DeleteMapping(value = "/usuario/{id}")
    public void eliminarUsuario(@PathVariable("id") Long id){
        Usuario user = usuarioRepository.getById(id);
        usuarioRepository.delete(user);
    }

    //Modificar datos del usuario
    @PutMapping(value = "/usuario/{id}")
    public Usuario editarUsuario(@PathVariable("id") Long id, Usuario user){
        Usuario nuevo_user = usuarioRepository.getById(id);
        nuevo_user.setNombre(user.getNombre());
        nuevo_user.setApellido(user.getApellido());
        nuevo_user.setDireccion(user.getDireccion());
        nuevo_user.setEmail(user.getEmail());
        nuevo_user.setCiudad(user.getCiudad());
        nuevo_user.setProvincia(user.getProvincia());
        nuevo_user.setPais(user.getPais());
        return usuarioRepository.save(nuevo_user);
    }

    //Usuarios de una ciudad dada
    @GetMapping(value = "/usuario/ciudad")
    public List<Usuario> usuariosDe(@RequestParam String ciudad){
        return usuarioRepository.getByCiudad(ciudad);
    }

    //Usuarios creados luego de una fecha dada
    @GetMapping(value = "/usuario/ciudad")
    public List<Usuario> usuariosDesdeFecha(@RequestParam Date desdeFecha){
        return usuarioRepository.getByDesdeFecha(desdeFecha);
    }
}
