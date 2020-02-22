package br.com.poli.puzzleN;

public class PuzzleMedio extends Puzzle {

	public PuzzleMedio(Jogador jogador) {
		super(jogador, Dificuldade.NORMAL);
		// cria um novo calculascore medio temporario e seta no score da superclasse
		CalculaScore temp = new CalculaMedio();
		this.setScore(temp);
	}

}
