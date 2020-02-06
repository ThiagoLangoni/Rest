package br.com.fiap.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.livros.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findFirstByUsername(String username);

}