package br.com.poli.puzzleN;

public class MovimentoInvalidoException extends Exception {

	// cria uma excecao para movimentos invalidos
	public MovimentoInvalidoException(String mensagem) {
		super(mensagem);
	}

}
