package br.com.fiap.livros.dto;

import java.time.ZonedDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDTO {

    @NotBlank
    private String titulo;

    @Min(1)
    private Integer quantidadeDePaginas;
    private String ISBN;

    @PastOrPresent
    private ZonedDateTime dataLancamento;

}