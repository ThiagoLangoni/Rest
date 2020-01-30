package br.com.fiap.livros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.livros.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
 
    List<Book> findByTituloStartsWith(String titulo);

    Optional<Book> findById(Integer id);
    
}



