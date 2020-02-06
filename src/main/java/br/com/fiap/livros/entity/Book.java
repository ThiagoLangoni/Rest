package br.com.fiap.livros.entity;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.livros.dto.CreateBookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_BOOK")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String titulo;
    
    @Column
    private Integer quantidadeDePaginas;
    
    @Column
    private String ISBN;
    
    @Column
    private ZonedDateTime dataLancamento;
    
    @ManyToOne
    private Autor autor;

    @Column
    @CreatedDate
    private Date dataCriacao;

    @Column
    @LastModifiedDate
    private Date dataAlteracao;

    public Book(CreateBookDTO createBookDTO) {
        this.titulo = createBookDTO.getTitulo();
        this.quantidadeDePaginas = createBookDTO.getQuantidadeDePaginas();
        this.ISBN = createBookDTO.getISBN();
        this.dataLancamento = createBookDTO.getDataLancamento();
        // this.autor
        
    }

}