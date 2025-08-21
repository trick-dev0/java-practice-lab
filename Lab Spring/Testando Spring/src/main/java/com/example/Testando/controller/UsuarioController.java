package com.example.Testando.controller;

import com.example.Testando.model.Usuario;
import com.example.Testando.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@RestController  // Retorna dados diretamente no corpo da resposta HTTP em JSON ou XML
@RequestMapping("/usuarios") // Mapeia requisisções
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public void salvar(@RequestBody Usuario usuario) { // Mapear o body de uma requisição HTTP para um objeto Java no metodo de um controller.
        try {
            // Geraçõa de números aleatórios
            String numId = UUID.randomUUID().toString(); // Converte em Strig

            usuario.setId(numId);// Atribui o id gerado aleatoriamente ao Usuario
            usuarioRepository.save(usuario);  // salva o Usuário
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (usuario != null) {
                System.out.println(usuario);
            }
        }
    }

    @GetMapping("/{id}")// O /id define que este metodo responderá a requisições HTTP GET na URL
    public Usuario buscarPorId(@PathVariable("id") String id) { // Extrai o valor {id} da URL e passa para o parâmetro id do metodo.
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        // Verifica se o usuário é nulo ou não
        return usuario.isPresent() ? usuario.get() : null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") String id){
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        /* Tem dois parâmetros no metodo,
        * - O primeiro para extrair o valor do id da requisição
        * - O segundo insere no body da requisição os dados atualizados*/

        usuario.setId(id); // Vou adicionar o id ao Usuário com dados atualizados
        usuarioRepository.save(usuario); // Utiliza-se o .save() para salvar o novo usuário
    }
}
