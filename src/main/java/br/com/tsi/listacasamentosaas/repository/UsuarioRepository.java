package br.com.tsi.listacasamentosaas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tsi.model.Usuario;
import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario,String> {

	List<Usuario> findByEmail(String email);
	List<Usuario> findByEmailAndSenha(String email, String senha);
	List<Usuario> findById(String id);
}


