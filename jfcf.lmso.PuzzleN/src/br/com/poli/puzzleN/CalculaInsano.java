package br.com.poli.puzzleN;

public class CalculaInsano implements CalculaScore{

	@Override
	public int pontos(Puzzle partida) {
		float pontos;
		pontos = (1/(float)partida.getQuantidadeMovimento())*10000000*partida.getGridPuzzle().getTamanhoGrid();
		return (int)pontos;
	}
}
