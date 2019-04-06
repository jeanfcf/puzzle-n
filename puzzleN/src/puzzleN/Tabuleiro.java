package puzzleN;

public class Tabuleiro {

	private Bloco[][] grid;

	// Inicia o tabuleiro de acordo com a dificuldade (nesse caso ja seta os valores
	// pedidos no pdf)
	public Tabuleiro(Dificuldade dificuldade) {

		int tamanhoMatriz;
		// tamanhoMatriz � responsavel por guardar o tamanho da matriz, usando o enum
		tamanhoMatriz = (int) (Math.sqrt(dificuldade.getValor() + 1));
		// inicializa grid usando tamanhoMatriz
		this.grid = new Bloco[tamanhoMatriz][tamanhoMatriz];
		// inicializa cada um dos blocos da matriz
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {
				this.grid[i][j] = new Bloco();
			}
		}
		// os valores iniciais exigidos no pdf
		this.grid[0][0].setValor(5);
		this.grid[0][1].setValor(2);
		this.grid[0][2].setValor(7);
		this.grid[1][0].setValor(8);
		this.grid[1][1].setValor(4);
		this.grid[1][2].setValor(0);
		this.grid[2][0].setValor(1);
		this.grid[2][1].setValor(3);
		this.grid[2][2].setValor(6);

		// seta true nas casas que podem ser movidas
		setValidos();
	}

	// metodo que troca a posicao do 0 com a posicao escolhida, se o movimento for
	// possível
	public void executaMovimento(int linha, int coluna, String sentido) {

		// seta true nas casas que podem ser movidas
		setValidos();
		// verifica se o bloco escolhido pode ser movido
		if (this.grid[linha][coluna].isValido()) {
			// caso pra cima, verifica se o valor esta dentro da matriz e se eh 0
			if (sentido.equals("cima") && linha - 1 >= 0 && this.grid[linha - 1][coluna].getValor() == 0) {
				// atribui o valor ao bloco em cima
				this.grid[linha - 1][coluna].setValor(this.grid[linha][coluna].getValor());
				// atribui 0 ao bloco anterior
				this.grid[linha][coluna].setValor(0);
				// seta true no bloco em cima, que agora pode ser movido
				this.grid[linha - 1][coluna].setValido(true);
				// seta false onde agora ha 0
				this.grid[linha][coluna].setValido(false);
			} else if (sentido.equals("baixo") && linha + 1 < this.grid.length
					&& this.grid[linha + 1][coluna].getValor() == 0) {

				// atribui o valor ao bloco em baixo
				this.grid[linha + 1][coluna].setValor(this.grid[linha][coluna].getValor());
				// atribui 0 ao bloco anterior
				this.grid[linha][coluna].setValor(0);
				// seta true no bloco em baixo, que agora pode ser movido
				this.grid[linha + 1][coluna].setValido(true);
				// seta false onde agora ha 0
				this.grid[linha][coluna].setValido(false);
			} else if (sentido.equals("direita") && linha + 1 < this.grid.length
					&& this.grid[linha][coluna + 1].getValor() == 0) {

				// atribui o valor ao bloco em direita
				this.grid[linha][coluna + 1].setValor(this.grid[linha][coluna].getValor());
				// atribui 0 ao bloco anterior
				this.grid[linha][coluna].setValor(0);
				// seta true no bloco na direita, que agora pode ser movido
				this.grid[linha][coluna + 1].setValido(true);
				// seta false onde agora ha 0
				this.grid[linha][coluna].setValido(false);
			} else if (sentido.equals("esquerda") && coluna - 1 >= 0 && this.grid[linha][coluna - 1].getValor() == 0) {

				// atribui o valor ao bloco em esquerda
				this.grid[linha][coluna - 1].setValor(this.grid[linha][coluna].getValor());
				// atribui 0 ao bloco anterior
				this.grid[linha][coluna].setValor(0);
				// seta true no bloco na esquerda, que agora pode ser movido
				this.grid[linha][coluna - 1].setValido(true);
				// seta false onde agora ha 0
				this.grid[linha][coluna].setValido(false);
			}
		}
	}

	// seta true nas casas que podem ser movidas de acordo com a posicao do zero
	public final void setValidos() {
		int j;
		// percorre a matriz
		for (int i = 0; i < this.grid.length; i++) {
			for (j = 0; j < this.grid.length; j++) {
				// acha o 0 e seta true nas pecas que podem ser movidas ao seu redor
				if (this.grid[i][j].getValor() == 0) {
					// verifica se esta dentro dos limites da matriz e se estiver,seta true na casa
					// de baixo
					if (i + 1 < this.grid.length) {
						this.grid[i + 1][j].setValido(true);
					}
					// verifica se esta dentro dos limites da matriz e se estiver, seta true na casa
					// de cima
					if (i - 1 >= 0) {
						this.grid[i - 1][j].setValido(true);
					}
					// verifica se esta dentro dos limites da matriz e se estiver,seta true na casa
					// da direita
					if (j + 1 < this.grid.length) {
						this.grid[i][j + 1].setValido(true);
					}
					// verifica se esta dentro dos limites da matriz e se estiver,seta true na casa
					// da esquerda
					if (j - 1 >= 0) {
						this.grid[i][j - 1].setValido(true);
					}
					break;
				}
			}
			if (this.grid[i][j - 1].getValor() == 0)
				break;
		}

	}

	public boolean isTabuleiroOrdenado() {
		// variavel que armazena a diferenca entre linhas
		int contador = 1;
		// for para percorrer a grid verificando se est� em ordem
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {
				if (i == j && i == this.grid.length - 1) {
					// se nao for zero na ultima casa retorna a falso
					if (this.grid[i][j].getValor() != 0) {
						return false;
					}
					// se nao for o numero apropriado para casa retorna a falso
				} else if (this.grid[i][j].getValor() != i + j + contador) {
					return false;
				}
			}
			contador += (this.grid.length - 1);
		}
		return true;
	}

	public void geraTabuleiro(Dificuldade dificuldade) {
	}
	
	//nao sei se pode fazer isso
	public Bloco getGrid(int i,int j) {
		return this.grid[i][j];
	}

	public void setGrid(Bloco[][] grid) {
		this.grid = grid;
	}

	// retorna o tamanho da matriz
	public int getTamanhoMatriz() {
		return this.grid.length;
	}

	// retorna o valor dentro da posição i e j da matriz
	public int getValorMatriz(int i, int j) {
		return this.grid[i][j].getValor();
	}
}
