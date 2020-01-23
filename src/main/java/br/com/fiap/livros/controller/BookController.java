package br.com.fiap.livros.controller;

import java.applet.AudioClip;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.livros.dto.AutorDTO;
import br.com.fiap.livros.dto.BookDTO;
import br.com.fiap.livros.dto.CreateBookDTO;

@RestController
@RequestMapping("books")
public class BookController {
    
    static List<BookDTO> bookList = new ArrayList<>();

    static {
        bookList.add(BookDTO.builder()
                            .id(1)
                            .titulo("O tombo da moto")
                            .autor(AutorDTO.builder().id(1).nome("Eu").build())
                            .dataLancamento(ZonedDateTime.now())
                            .ISBN("ISBN")
                            .quantidadeDePaginas(1)
                            .build());

        bookList.add(BookDTO.builder()
                            .id(2)
                            .titulo("O tombo da moto 2")
                            .autor(AutorDTO.builder().id(1).nome("Eu").build())
                            .dataLancamento(ZonedDateTime.now())
                            .ISBN("ISBN")
                            .quantidadeDePaginas(1)
                            .build());                            

        bookList.add(BookDTO.builder()
                            .id(3)
                            .titulo("O tombo da moto 3")
                            .autor(AutorDTO.builder().id(1).nome("Eu").build())
                            .dataLancamento(ZonedDateTime.now())
                            .ISBN("ISBN")
                            .quantidadeDePaginas(1)
                            .build());     
    }

    @GetMapping
    public List<BookDTO> getAll(@RequestParam(required = false, value = "title") String titulo) {
        // @formater:off
        return bookList.stream()
                       .filter(bookDTO -> titulo == null || bookDTO.getTitulo().startsWith(titulo))
                       .collect(Collectors.toList());
        // @formater:on
    }

    @GetMapping("{id}")
    public BookDTO findById(@PathVariable Integer id) {
        // @formater:off
        return bookList.stream()
                       .filter(bookDTO -> bookDTO.getId() == id)
                       .findFirst()
                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // @formater:on
    }

    @PostMapping
    public BookDTO create(@RequestBody CreateBookDTO createBookDTO) {
        // @formater:off
        BookDTO book = new BookDTO(createBookDTO, bookList.size() + 1);
        bookList.add(book);
        return book;
        // @formater:on
    }

    @PutMapping("{id}")
    public BookDTO update(@PathVariable Integer id,
                          @RequestBody CreateBookDTO createBookDTO) {
        // @formater:off
        BookDTO book = bookList.stream()
                               .filter(bookDTO -> bookDTO.getId() == id)
                               .findFirst()
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        book.setDataLancamento(createBookDTO.getDataLancamento());
        book.setISBN(createBookDTO.getISBN());
        book.setQuantidadeDePaginas(createBookDTO.getQuantidadeDePaginas());
        book.setTitulo(createBookDTO.getTitulo());

        return book;
        // @formater:on
    }


    @PatchMapping("{id}")
    public BookDTO update(@PathVariable Integer id,
                          @RequestBody AutorDTO autor) {
        // @formater:off
        BookDTO book = bookList.stream()
                               .filter(bookDTO -> bookDTO.getId() == id)
                               .findFirst()
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.setAutor(autor);

        return book;

        // @formater:on                            
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        // @formater:off
        BookDTO book = bookList.stream()
                                .filter(bookDTO -> bookDTO.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        bookList.remove(book);
        // @formater:on
    }


}




