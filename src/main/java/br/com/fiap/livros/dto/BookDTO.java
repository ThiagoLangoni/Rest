package br.com.fiap.livros.dto;

import java.time.ZonedDateTime;
import java.util.Date;

import br.com.fiap.livros.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    public BookDTO(CreateBookDTO createBookDTO, Integer id) {
        this.id = id;
        this.titulo = createBookDTO.getTitulo();
        this.quantidadeDePaginas = createBookDTO.getQuantidadeDePaginas();
        this.ISBN = createBookDTO.getISBN();
        this.dataLancamento = createBookDTO.getDataLancamento();
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.titulo = book.getTitulo();
        this.quantidadeDePaginas = book.getQuantidadeDePaginas();
        this.ISBN = book.getISBN();
        this.dataLancamento = book.getDataLancamento();
        this.dataCriacao = book.getDataCriacao();
        this.dataAlteracao = book.getDataAlteracao();
        // this.autor = new AutorDTO(book.getAutor());
    }

	private Integer id;
    private String titulo;
    private Integer quantidadeDePaginas;
    private String ISBN;
    private ZonedDateTime dataLancamento;
    private AutorDTO autor;
    private Date dataCriacao;
    private Date dataAlteracao;

}