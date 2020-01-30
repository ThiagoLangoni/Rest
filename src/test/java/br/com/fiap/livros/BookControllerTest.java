package br.com.fiap.livros;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.livros.controller.BookController;
import br.com.fiap.livros.dto.BookDTO;

@SpringBootTest
public class BookControllerTest {

    @Test
    public void getAllTest() {
        
        // //arrange
        // BookController bookController = new BookController();

        // //act
        // List<BookDTO> bookList = bookController.getAll(null);

        // //result
        // assertEquals(bookList.size(), 3);
    }

}
