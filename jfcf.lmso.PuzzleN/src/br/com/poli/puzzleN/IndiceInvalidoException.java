package br.com.poli.puzzleN;

public class IndiceInvalidoException extends Exception {

	// cria uma excecao para indices invalidos na grid
	public IndiceInvalidoException(String mensagem) {
		super(mensagem);
	}

}
