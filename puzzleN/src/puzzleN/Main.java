package puzzleN;

public class Main {
	public static void main(String[] args) {
		
		//cria um novo jogador
		Jogador jogador = new Jogador("jogador");
		//cria um puzzle iniciando com o nome do jogador e a dificuldade facil
		Puzzle teste = new Puzzle(jogador, Dificuldade.FACIL);
		//cria e instancia um tabuleiro para teste, já que não foi pedido isso no
		//pdf
		Tabuleiro teste1 = new Tabuleiro(Dificuldade.FACIL);
		//seta o tabuleiro criado em puzzle
		teste.setGridPuzzle(teste1);
		//inicia a partida
		teste.iniciaPartida();
		//verifica se está ordenado
		System.out.println(teste.getGridPuzzle().isTabuleiroOrdenado());
		
		//printa a matriz
		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}
		
		//faz o movimento do 4 para direita na matriz
		teste.getGridPuzzle().executaMovimento(1, 1, "direita");
		
		//printa uma linha para melhor visualização no console
		System.out.println(" ");
		
		//printa a matriz com a nova posição do 4 e do 0
		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}
		
		//muda a posição do 2 para baixo
		teste.getGridPuzzle().executaMovimento(0, 1, "baixo");
		
		//printa uma linha para melhor visualização no console
		System.out.println(" ");
		
		//printa a matriz com a nova posição do 2 e do 0
		for (int i = 0; i < teste.getGridPuzzle().getTamanhoMatriz(); i++) {
			for (int j = 0; j < teste.getGridPuzzle().getTamanhoMatriz(); j++) {
				System.out.print(teste.getGridPuzzle().getValorMatriz(i, j) + " ");
				if (j == 2) {
					System.out.println();
				}
			}
		}
		
		//muda a posição do 7 para esquerda
		teste.getGridPuzzle().executaMovimento(0, 2, "esquerda");
		
		//printa uma linha para melhor visualização no console
		System.out.println(" ");
		
		//printa a matriz com a nova posição do 7 e do 0
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
