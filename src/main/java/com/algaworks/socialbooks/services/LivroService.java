package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.exceptions.LivroNaoEncontradoException;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;

@Service
public class LivroService {

	@Autowired
	private LivrosRepository lp;
	
	@Autowired
	private ComentariosRepository cr;

	public List<Livro> listar() {
		return lp.findAll();
	}

	public Livro buscar(Long id) {
		Optional<Livro> livro = lp.findById(id);

		if (livro.isEmpty()) {
			throw new LivroNaoEncontradoException("O livro nao foi encontrado.");
		}
		return livro.get();
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		livro = lp.save(livro);

		return livro;
	}

	public void deletar(Long id) {
		try {
			lp.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro nao encontrado.");
		}
	}

	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		lp.save(livro);
	}

	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long idLivro, Comentario comentario) {
		Livro livro = buscar(idLivro);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return cr.save(comentario);
	}

}
