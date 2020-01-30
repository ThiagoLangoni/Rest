package br.com.fiap.livros.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "TB_AUTOR")
public class Autor {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String nome;

}