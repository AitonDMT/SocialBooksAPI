package com.algaworks.socialbooks.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {


	private static final long serialVersionUID = 1509006076866718311L;

	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public LivroNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
