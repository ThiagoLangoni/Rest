package br.com.fiap.livros.service;

import br.com.fiap.livros.dto.AuthDTO;
import br.com.fiap.livros.dto.CreatedUserDTO;
import br.com.fiap.livros.dto.JwtDTO;
import br.com.fiap.livros.dto.UserDTO;

public interface UserService {
    
    JwtDTO login(AuthDTO AuthDTO);

    UserDTO create(CreatedUserDTO CreateUserDTO);

}