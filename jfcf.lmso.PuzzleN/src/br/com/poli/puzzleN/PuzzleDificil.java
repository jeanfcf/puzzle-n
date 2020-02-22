package br.com.poli.puzzleN;

public class PuzzleDificil extends Puzzle{

	public PuzzleDificil(Jogador jogador) {
		super(jogador, Dificuldade.DIFICIL);
		// cria um novo calculascore dificil temporario e seta no score da superclasse
		CalculaScore temp = new CalculaDificil();
		this.setScore(temp);
	}

}
