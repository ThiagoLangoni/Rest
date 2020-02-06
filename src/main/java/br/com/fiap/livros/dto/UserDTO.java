package br.com.fiap.livros.dto;

import br.com.fiap.livros.entity.User;
import lombok.Data;

@Data
public class UserDTO {
  
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
    
	private Integer id;
    private String username;
    private String password;


}