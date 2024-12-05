package com.adatech.filmes_API.service.UsuarioService;

import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.mapper.UsuarioDTOMapper;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.exception.CpfDuplicadoException;
import com.adatech.filmes_API.exception.EmailDuplicadoException;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService {
    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioService(UsuarioRepository UsuarioRepository) {
        this.usuarioRepository = UsuarioRepository;
    }

    public UsuarioResponseDTO execute(CriarUsuarioDTO usuarioParaSalvar) {
        if (usuarioRepository.existsByCpf(usuarioParaSalvar.getCpf())) {
            throw new CpfDuplicadoException("CPF já cadastrado no sistema.");
        }
        if (usuarioRepository.existsByEmail(usuarioParaSalvar.getEmail())) {
            throw new EmailDuplicadoException("Email já cadastrado no sistema.");
        }

        Usuario usuarioEntity = UsuarioDTOMapper.toEntity(usuarioParaSalvar);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioEntity);
        return UsuarioDTOMapper.toResponse(usuarioSalvo);
    }
}