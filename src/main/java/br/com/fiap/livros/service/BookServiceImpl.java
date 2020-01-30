package br.com.fiap.livros.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.livros.dto.AutorDTO;
import br.com.fiap.livros.dto.BookDTO;
import br.com.fiap.livros.dto.CreateBookDTO;
import br.com.fiap.livros.entity.Book;
import br.com.fiap.livros.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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


    @Override
    public List<BookDTO> getAll(String titulo) {
        // @formater:off

        return this.bookRepository.findByTituloStartsWith(titulo)
                                  .stream()
                                  .map(book -> new BookDTO(book))
                                  .collect(Collectors.toList());

        // return bookList.stream()
        //                .filter(bookDTO -> titulo == null || bookDTO.getTitulo().startsWith(titulo))
        //                .collect(Collectors.toList());
        // @formater:on
    }

    @Override
    public BookDTO findById(Integer id) {
        // @formater:off
        Book book = this.bookRepository
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        return new BookDTO(book);

        // return bookList.stream()
        //                .filter(bookDTO -> bookDTO.getId() == id)
        //                .findFirst()
        //                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // @formater:on
    }

    @Override
    public BookDTO create(CreateBookDTO createBookDTO) {
        // @formater:off
        Book book = new Book(createBookDTO);
        Book savedBook = this.bookRepository.save(book);
        return new BookDTO(savedBook);

        // BookDTO book = new BookDTO(createBookDTO, bookList.size() + 1);
        // bookList.add(book);
        // return book;
        // @formater:on
    }

    @Override
    public BookDTO update(Integer id, CreateBookDTO createBookDTO) {
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

    @Override
    public BookDTO update(Integer id, AutorDTO autor) {
        // @formater:off
        BookDTO book = bookList.stream()
                               .filter(bookDTO -> bookDTO.getId() == id)
                               .findFirst()
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.setAutor(autor);

        return book;

        // @formater:on
    }

    @Override
    public void delete(Integer id) {
        // @formater:off
        BookDTO book = bookList.stream()
                                .filter(bookDTO -> bookDTO.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        bookList.remove(book);
        // @formater:on
    }





}