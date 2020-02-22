package br.com.poli.puzzleN;

public enum Dificuldade {
	FACIL(8), NORMAL(15), DIFICIL(24) , INSANO(0);
	private int valor;

	private Dificuldade(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return this.valor;
	}
}