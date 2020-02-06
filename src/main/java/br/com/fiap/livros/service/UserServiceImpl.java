package br.com.fiap.livros.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.livros.dto.AuthDTO;
import br.com.fiap.livros.dto.CreatedUserDTO;
import br.com.fiap.livros.dto.JwtDTO;
import br.com.fiap.livros.dto.UserDTO;
import br.com.fiap.livros.entity.User;
import br.com.fiap.livros.repository.UserRepository;
import br.com.fiap.livros.security.JwtTokenUtil;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthenticationManager authenticationManager
                         , JwtTokenUtil jwtTokenUtil
                         , UserRepository userRepository
                         , PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtDTO login(AuthDTO AuthDTO) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(AuthDTO.getUsername(), AuthDTO.getPassword()));

        String token = this.jwtTokenUtil.generateToken(AuthDTO.getUsername());
        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setToken(token);
        return jwtDTO;
    }

    @Override
    public UserDTO create(CreatedUserDTO CreateUserDTO) {
        User user = new User();
        user.setUsername(CreateUserDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(CreateUserDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser);
    }
    
}