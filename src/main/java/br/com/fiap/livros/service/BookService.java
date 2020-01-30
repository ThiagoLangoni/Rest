package br.com.fiap.livros.service;

import java.util.List;

import br.com.fiap.livros.dto.AutorDTO;
import br.com.fiap.livros.dto.BookDTO;
import br.com.fiap.livros.dto.CreateBookDTO;

public interface BookService {

    List<BookDTO> getAll(String titulo);

    BookDTO findById(Integer id);

    BookDTO create(CreateBookDTO createBookDTO);

    BookDTO update(Integer id, CreateBookDTO createBookDTO);

    BookDTO update(Integer id, AutorDTO autor);

    void delete(Integer id);
    
}