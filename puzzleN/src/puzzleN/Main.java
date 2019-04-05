package puzzleN;

public class Main {
	public static void main(String[] args) {

		Jogador jogador = new Jogador("jogador");
		Puzzle teste = new Puzzle(jogador, Dificuldade.FACIL);

		teste.iniciaPartida();
		System.out.println(teste.getGridPuzzle().isTabuleiroOrdenado());

		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}

		teste.getGridPuzzle().executaMovimento(1, 1, "direita");

		System.out.println(" ");

		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}

		teste.getGridPuzzle().executaMovimento(0, 1, "baixo");

		System.out.println(" ");

		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}

		teste.getGridPuzzle().executaMovimento(0, 2, "esquerda");

		System.out.println(" ");

		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}
	}
}
