package br.com.fiap.livros.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.livros.dto.AuthDTO;
import br.com.fiap.livros.dto.CreatedUserDTO;
import br.com.fiap.livros.dto.JwtDTO;
import br.com.fiap.livros.dto.UserDTO;
import br.com.fiap.livros.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public JwtDTO postMethodName(@RequestBody AuthDTO authDTO) {
        return this.userService.login(authDTO);
    }

    @PostMapping()
    public UserDTO create(@RequestBody CreatedUserDTO user) {
        return this.userService.create(user);
    }

}