package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.service.CriarUsuarioService;
import com.adatech.filmes_API.service.ObterUsuarioPorIdService;
import com.adatech.filmes_API.service.ObterUsuariosPorFiltroService;
import com.adatech.filmes_API.service.ObterUsuariosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final ObterUsuarioPorIdService obterUsuarioPorIdService;
    private final CriarUsuarioService criarUsuarioService;
    private final ObterUsuariosPorFiltroService obterUsuariosPorFiltro;
    private final ObterUsuariosService obterUsuariosService;

    public UsuarioController(ObterUsuarioPorIdService obterUsuarioPorIdService, CriarUsuarioService criarUsuarioService, ObterUsuariosPorFiltroService obterUsuariosPorFiltro, ObterUsuariosService obterUsuariosService) {
        this.obterUsuarioPorIdService = obterUsuarioPorIdService;
        this.criarUsuarioService = criarUsuarioService;
        this.obterUsuariosPorFiltro = obterUsuariosPorFiltro;
        this.obterUsuariosService = obterUsuariosService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarUsuarioService.execute(usuario));
    }

    @GetMapping("/{id}")
    public Usuario obterUsuarioPorId(@PathVariable Long id) {
        return obterUsuarioPorIdService.execute(id);
    }

    @GetMapping
    public Page<Usuario> obterUsuariosPaginados(Pageable pageable) {
        return obterUsuariosService.execute(pageable);
    }

    @GetMapping("/nome")
    public List<Usuario> obterUsuariosPorNome(@RequestParam String nome) {
        return obterUsuariosPorFiltro.obterUsuarioPorNome(nome);
    }

    @GetMapping("/genero")
    public List<Usuario> obterUsuariosPorGenero(@RequestParam String genero) {
        return obterUsuariosPorFiltro.obterUsuarioPorGenero(genero);
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
