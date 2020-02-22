package br.com.poli.puzzleN;

public class PuzzleFacil extends Puzzle{

	public PuzzleFacil(Jogador jogador) {
		super(jogador, Dificuldade.FACIL);
		// cria um novo calculascore facil temporario e seta no score da superclasse
		CalculaScore temp = new CalculaFacil();
		this.setScore(temp);
		
	}

}
