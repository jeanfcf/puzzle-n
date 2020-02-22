package br.com.poli.puzzleN;
/*
 * Jean Felipe Coelho Ferreira 
 * Lucas Matheus da Silva Oliveira
 * 
 * Turma KW - Professor BRUNO 
 * 
 * Eclipse
 */

public class Main {
	public static void main(String[] args) {

		Puzzle teste = new PuzzleFacil(new Jogador("oioi"));
		Puzzle teste0 = new PuzzleFacil(new Jogador("naej e sacul"));
		Puzzle teste1 = new PuzzleMedio(new Jogador("luscas e jeanus"));
		Puzzle teste2 = new PuzzleDificil(new Jogador("matheus e felipe"));
		Puzzle teste3; 
		Puzzle teste4 = new PuzzleInsano(new Jogador("sofro"),15);

		
		System.out.println("------------------");
		System.out.println("TESTE PUZZLE FACIL");
		System.out.println("JOGADOR: " +teste.getJogador().getNome());
		System.out.println();
		// testa grid facil 
		teste.iniciaPartida();
		teste.getGridPuzzle().getPosicaoGrid(0, 0).setValor(1);
		teste.getGridPuzzle().getPosicaoGrid(0, 1).setValor(2);
		teste.getGridPuzzle().getPosicaoGrid(0, 2).setValor(3);
		teste.getGridPuzzle().getPosicaoGrid(1, 0).setValor(4);
		teste.getGridPuzzle().getPosicaoGrid(1, 1).setValor(0);
		teste.getGridPuzzle().getPosicaoGrid(1, 2).setValor(5);
		teste.getGridPuzzle().getPosicaoGrid(2, 0).setValor(7);
		teste.getGridPuzzle().getPosicaoGrid(2, 1).setValor(8);
		teste.getGridPuzzle().getPosicaoGrid(2, 2).setValor(6);
		teste.getGridPuzzle().setValidos();

		System.out.println(" ");
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();
		System.out.println();
		
		// faz o movimento invalido para mostrar o erro
		teste.fazMovimento(1, 1);
		System.out.println();
		System.out.println("Venceu? " + teste.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste.getQuantidadeMovimento());
		System.out.println();
		System.out.println("------------------");
		System.out.println();
		
		teste.fazMovimento(1, 2 );
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();
		System.out.println("Venceu? " + teste.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste.getQuantidadeMovimento());
		System.out.println();
		System.out.println("-----------------------");
		
		System.out.println(" ");
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();
		System.out.println();
		
		// tentar fazer um movimento fora do limite da matriz para mostrar um erro
		teste.fazMovimento(4, 3);
		System.out.println();
		System.out.println("Venceu? " + teste.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste.getQuantidadeMovimento());
		System.out.println();
		System.out.println("------------------");
		
		//faz movimento para vencer o jogo
		teste.fazMovimento(2, 2);
		System.out.println(" ");
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();
		System.out.println();
		System.out.println();
		System.out.println("Venceu? " + teste.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste.getQuantidadeMovimento());
		System.out.println();
		System.out.println("Score: "+ teste.getScore());
		System.out.println();
		System.out.println("------------------");
		
		//faz movimento depois de ter vencido para lançar o erro
		teste.fazMovimento(1, 2);
		System.out.println(" ");
		teste.imprimeGrid();			
		System.out.println();		
		teste.imprimeGridValido();
		System.out.println();
		System.out.println();
		System.out.println("Venceu? " + teste.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste.getQuantidadeMovimento());
		System.out.println();
		System.out.println("Score: "+ teste.getScore());
		System.out.println();
		System.out.println("------------------");
		
		/*//testa grid facil com a disposicao de numeros aleatorios
		teste0.iniciaPartida();
		teste0.getGridPuzzle().setValidos();
		System.out.println("TESTE PUZZLE FACIL ALEATORIO\n");
		System.out.println("JOGADOR: " + teste1.getJogador().getNome());
		System.out.println();
		teste0.imprimeGrid();
		System.out.println();
		teste0.imprimeGridValido();
		System.out.println();
		System.out.println("Venceu? " + teste1.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste2.getQuantidadeMovimento());
		System.out.println();
		System.out.println("-----------------------");
		
		//testa grid medio com a disposicao de numeros aleatorios
		teste1.iniciaPartida();
		teste1.getGridPuzzle().setValidos();
		System.out.println("TESTE PUZZLE MEDIO\n");
		System.out.println("JOGADOR: " + teste1.getJogador().getNome());
		System.out.println();
		teste1.imprimeGrid();
		System.out.println();
		teste1.imprimeGridValido();
		System.out.println();
		System.out.println("Venceu? " + teste1.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste2.getQuantidadeMovimento());
		System.out.println();
		System.out.println("-----------------------");
		
		//testa grid dificil com a disposicao de numeros aleatorios
		teste2.iniciaPartida();
		teste2.getGridPuzzle().setValidos();
		System.out.println("TESTE PUZZLE DIFICIL\n");
		System.out.println("JOGADOR: " +teste2.getJogador().getNome());
		System.out.println();
		teste2.imprimeGrid();
		System.out.println();
		teste2.imprimeGridValido();
		System.out.println();
		System.out.println("Venceu? " + teste2.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste2.getQuantidadeMovimento());
		System.out.println();
		System.out.println("--------------------------------------");
		
		
		//testa grid insano com a disposição de numeros aleatorios
		//e com valor do tabuleiro menor que o limite pra mostrar o erro
		teste3 = new PuzzleInsano(new Jogador("suehtam e epilef"),-3);
		teste3.iniciaPartida();
		teste3.getGridPuzzle().setValidos();
		System.out.println();
		System.out.println("TESTE PUZZLE INSANO\n");
		System.out.println("JOGADOR: " +teste3.getJogador().getNome());
		System.out.println();
		teste3.imprimeGrid();
		System.out.println();
		teste3.imprimeGridValido();
		System.out.println();
		System.out.println("Venceu? " + teste3.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste3.getQuantidadeMovimento());
		System.out.println();
		System.out.println("------------------------------");
		
		//testa grid insano com a disposição de numeros aleatorios
		teste4.iniciaPartida();
		teste4.getGridPuzzle().setValidos();
		System.out.println("TESTE PUZZLE INSANO\n");
		System.out.println("JOGADOR: " + teste4.getJogador().getNome());
		System.out.println();
		teste4.imprimeGrid();
		System.out.println();
		teste4.imprimeGridValido();
		System.out.println();
		System.out.println("Venceu? " + teste4.isVenceu());
		System.out.println();
		System.out.println("quantidade de movimentos: "+ teste4.getQuantidadeMovimento());
		System.out.println();
		System.out.println("------------------------------");
		
		

		/*Testes usados na primeira parte do projeto 
		
		Puzzle teste = new Puzzle(new Jogador("jean e lucas"), Dificuldade.FACIL);

		System.out.println();
		System.out.println(teste.getJogador().getNome());
		System.out.println();
		System.out.println("-----------------");

		// testa grid inicial
		teste.iniciaPartida();
		System.out.println(" ");
		teste.imprimeGrid();
		System.out.println();
		// imprime grid com os valores validos
		teste.imprimeGridValido();

		// faz o movimento do 4 para direita na matriz
		teste.getGridPuzzle().executaMovimento(1, 1, "direita");
		System.out.println();
		// printa se o tabuleiro esta ordenado ou nao
		System.out.println("ordenado? " + teste.getGridPuzzle().isTabuleiroOrdenado());
		System.out.println();
		System.out.println("-----------------");
		System.out.println();

		// printa a matriz com a nova posição do 4 e do 0
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();

		// muda a posição do 2 para baixo
		teste.getGridPuzzle().executaMovimento(0, 1, "baixo");
		System.out.println();
		System.out.println("ordenado? " + teste.getGridPuzzle().isTabuleiroOrdenado());
		System.out.println();
		System.out.println("-----------------");
		System.out.println();

		// printa a matriz com a nova posição do 2 e do 0
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();

		// muda a posição do 7 para esquerda
		teste.getGridPuzzle().executaMovimento(0, 2, "esquerda");
		System.out.println();
		System.out.println("ordenado? " + teste.getGridPuzzle().isTabuleiroOrdenado());
		System.out.println();
		System.out.println("-----------------");
		System.out.println();

		// printa a matriz com a nova posição do 7 e do 0
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();

		// muda a posição do 4 para cima
		teste.getGridPuzzle().executaMovimento(1, 2, "cima");
		System.out.println();
		System.out.println("ordenado? " + teste.getGridPuzzle().isTabuleiroOrdenado());
		System.out.println();
		System.out.println("-----------------");
		System.out.println();

		// printa a matriz com a nova posição do 4 e do 0
		teste.imprimeGrid();
		System.out.println();
		teste.imprimeGridValido();
		System.out.println();
		System.out.println("ordenado? " + teste.getGridPuzzle().isTabuleiroOrdenado());
		System.out.println();
		System.out.println("-----------------");
		System.out.println();

		// seta valores ordenados a matriz
		teste.getGridPuzzle().getPosicaoGrid(0, 0).setValor(1);
		teste.getGridPuzzle().getPosicaoGrid(0, 1).setValor(2);
		teste.getGridPuzzle().getPosicaoGrid(0, 2).setValor(3);
		teste.getGridPuzzle().getPosicaoGrid(1, 0).setValor(4);
		teste.getGridPuzzle().getPosicaoGrid(1, 1).setValor(5);
		teste.getGridPuzzle().getPosicaoGrid(1, 2).setValor(6);
		teste.getGridPuzzle().getPosicaoGrid(2, 0).setValor(7);
		teste.getGridPuzzle().getPosicaoGrid(2, 1).setValor(8);
		teste.getGridPuzzle().getPosicaoGrid(2, 2).setValor(0);
		teste.getGridPuzzle().setValidos();

		teste.imprimeGrid();
		System.out.println();

		teste.imprimeGridValido();

		// verifica se está ordenado
		System.out.println();
		System.out.println("ordenado? " + teste.getGridPuzzle().isTabuleiroOrdenado());*/

	}
}