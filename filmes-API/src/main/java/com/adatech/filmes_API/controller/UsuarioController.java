package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.service.UsuarioService.CriarUsuarioService;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuarioPorIDService;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuariosPorFiltroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final ObterUsuarioPorIDService obterUsuarioPorIdService;
    private final CriarUsuarioService criarUsuarioService;
    private final ObterUsuariosPorFiltroService obterUsuariosPorFiltro;

    public UsuarioController(ObterUsuarioPorIDService obterUsuarioPorIdService, CriarUsuarioService criarUsuarioService,
                             ObterUsuariosPorFiltroService obterUsuariosPorFiltro) {
        this.obterUsuarioPorIdService = obterUsuarioPorIdService;
        this.criarUsuarioService = criarUsuarioService;
        this.obterUsuariosPorFiltro = obterUsuariosPorFiltro;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioResponseDTO criarUsuario(@Valid @RequestBody CriarUsuarioDTO usuario) {
        return criarUsuarioService.execute(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(obterUsuarioPorIdService.execute(id));
    }

    @GetMapping("/nome")
    public List<Usuario> obterUsuariosPorNome(@RequestParam String nome) {
        return obterUsuariosPorFiltro.obterUsuarioPorNome(nome);
    }


    @GetMapping("/cpf")
    public Usuario obterUsuariosPorCpf(@RequestParam String cpf) {
        return obterUsuariosPorFiltro.obterUsuarioPorCpf(cpf);
    }

    @GetMapping("/email")
    public Usuario ObterUsuarioPorEmail(@RequestParam String email) {
        return obterUsuariosPorFiltro.obterUsuarioPorEmail(email);
    }
}