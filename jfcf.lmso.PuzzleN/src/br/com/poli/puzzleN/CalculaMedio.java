package br.com.poli.puzzleN;

public class CalculaMedio implements CalculaScore{

	@Override
	public int pontos(Puzzle partida) {
		float pontos;
		pontos = (1/(float)partida.getQuantidadeMovimento())*100000*partida.getGridPuzzle().getTamanhoGrid()*partida.getGridPuzzle().getTamanhoGrid();
		return (int)pontos;
	}
}
