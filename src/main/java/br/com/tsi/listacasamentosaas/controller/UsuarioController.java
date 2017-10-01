package br.com.tsi.listacasamentosaas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tsi.model.Usuario;
import br.com.tsi.listacasamentosaas.repository.UsuarioRepository;

@RestController
@CrossOrigin
@RequestMapping(value="/usuario")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@GetMapping( value = "/findall/" )
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping( value = "/save/" )
	public void save(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@GetMapping(value="/findId/{id}")
    public ResponseEntity<Usuario> getTodoById(@PathVariable("id") String id) {
        Usuario usuario = usuarioRepository.findOne(id);
        if(usuario == null) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
    }
	
	@GetMapping(value = "/findbymail/{email}")
	private List<Usuario> findByEmail(@PathVariable(value="email") String q)
	{
		return usuarioRepository.findByEmail(q);
	}

	@GetMapping(value = "/findbymailandpassword/{email}/{senha}")
	private List<Usuario> findByEmailAndPassword(@PathVariable(value="email") String mail, @PathVariable(value="senha") String senha )
	{
		return usuarioRepository.findByEmailAndSenha(mail,senha);
	}
	
	@DeleteMapping( value = "/delete/{idUsuario}" )
	public void delete(@PathVariable(value="idUsuario") String idUsuario ) {
		if( idUsuario != null && idUsuario.trim() != "" )
		{
			List<Usuario> searchedUsers = usuarioRepository.findById(idUsuario);
						
			if(searchedUsers != null && searchedUsers.size() > 0)
			{
				Usuario toDelete = new Usuario();
				toDelete.setId(idUsuario);
				
				usuarioRepository.delete(toDelete);
			}
		}
	}
	
	@PostMapping( value = "/update/" )
	public void update(@RequestBody Usuario usuario) {
		
		if( usuario != null && usuario.getId() != null && usuario.getId().trim() != "" )
		{
			List<Usuario> searchedUsers = usuarioRepository.findById(usuario.getId());
						
			if(searchedUsers != null && searchedUsers.size() > 0)
				usuarioRepository.save(usuario);
		}
	}

}


