package com.example.Testando.controller;

import com.example.Testando.model.Usuario;
import com.example.Testando.repository.UsuarioRepository;

public class UsuarioController{
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


}
