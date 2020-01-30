package br.com.fiap.livros.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.livros.dto.AutorDTO;
import br.com.fiap.livros.dto.BookDTO;
import br.com.fiap.livros.dto.CreateBookDTO;
import br.com.fiap.livros.service.BookService;

@RestController
@RequestMapping("books")
public class BookController {
    
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAll(@RequestParam(required = false, value = "title") String titulo) {
        // @formater:off
        return this.bookService.getAll(titulo);
        // @formater:on
    }

    @GetMapping("{id}")
    public BookDTO findById(@PathVariable Integer id) {
        // @formater:off
        return this.bookService.findById(id);
        // @formater:on
    }

    @PostMapping
    public BookDTO create(@RequestBody @Valid CreateBookDTO createBookDTO) {
        // @formater:off
        return this.bookService.create(createBookDTO);
        // @formater:on
    }

    @PutMapping("{id}")
    public BookDTO update(@PathVariable Integer id,
                          @RequestBody CreateBookDTO createBookDTO) {
        // @formater:off
        return this.bookService.update(id, createBookDTO);
        // @formater:on
    }

    @PatchMapping("{id}")
    public BookDTO update(@PathVariable Integer id,
                          @RequestBody AutorDTO autor) {
        // @formater:off
        return this.bookService.update(id, autor);
        // @formater:on                            
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        // @formater:off
        this.bookService.delete(id);
        // @formater:on
    }
}




