package br.com.apiteste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiteste.model.Usuario;
import br.com.apiteste.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@GetMapping("acesso/{email}/{password}")
	public Usuario comparar(@PathVariable String email,@PathVariable String password) {
		
		String passwordBD= usuarioRepository.findByEmail(email);
	
		Usuario usuario = new Usuario();
		
		
		if(passwordBD==null) {
			usuario.setSituacao("O email não existe na base de dados");
			return usuario;
		}
		else if(!passwordBD.equals(password)) {
			usuario.setSituacao("Senha incorreta");
			return usuario;
		}
		
		else {
			usuario.setSituacao("Acesso Liberado");
			return usuario;
		}
	}

		

}
