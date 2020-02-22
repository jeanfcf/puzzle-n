package br.com.poli.puzzleN;

import java.util.Random;

public class Tabuleiro {

	private Bloco[][] grid;
	//atributo para armazenar os numeros em suas determinadas posicoes certas
	private int[][] posicaoCerta;

	// Inicia o tabuleiro de acordo com a dificuldade

	public Tabuleiro(Dificuldade dificuldade) {
		int contador = 1;
		int tamanhoMatriz;
		// tamanhoMatriz � responsavel por guardar o tamanho da matriz, usando o enum
		tamanhoMatriz = (int) (Math.sqrt(dificuldade.getValor() + 1));
		// inicializa grid usando tamanhoMatriz
		this.grid = new Bloco[tamanhoMatriz][tamanhoMatriz];
		posicaoCerta = new int[grid.length][grid.length];
		// inicializa cada um dos blocos da matriz e seta valores ordenados neles
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {
				this.grid[i][j] = new Bloco();
				this.grid[i][j].setValor(contador);
				posicaoCerta[i][j]=contador;
				contador++;
				if (i == j && j == this.grid.length - 1) {
					this.grid[i][j].setValor(0);
				posicaoCerta[i][j]=contador;
				}
			}
		}
		// seta true nas casas que podem ser movidas
		setValidos();
	}

	// construtor para o puzzle insano
	public Tabuleiro(Dificuldade dificuldade, int k) {
		int contador = 1;
		// inicializa grid usando tamanhoMatriz
		this.grid = new Bloco[k][k];
		posicaoCerta = new int[grid.length][grid.length];
		// inicializa cada um dos blocos da matriz e seta valores ordenados neles
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {
				this.grid[i][j] = new Bloco();
				this.grid[i][j].setValor(contador);
				posicaoCerta[i][j]=contador;
				contador++;
				if (i == j && j == this.grid.length - 1) {
				this.grid[i][j].setValor(0);
				posicaoCerta[i][j]=0;
				}
			}
		}
		// seta true nas casas que podem ser movidas
		setValidos();
	}
	
	//retorna a posicao certa i,j 
	public int getPosicaoCerta(int i, int j) {
		return this.posicaoCerta[i][j];
	}

	public Bloco[][] getGrid() {
		return grid;
	}

	public void setGrid(Bloco[][] grid) {
		this.grid = grid;
	}

	// retorna a matriz na posição i e j
	public Bloco getPosicaoGrid(int i, int j) {
		return this.grid[i][j];
	}

	// retorna o tamanho da matriz
	public int getTamanhoGrid() {
		return this.grid.length;
	}

	// retorna o valor dentro da posição i e j da matriz
	public int getValorGrid(int i, int j) {
		return this.grid[i][j].getValor();
	}

	// metodo que troca a posicao do 0 com a posicao escolhida, se o movimento for
	// possível
	public boolean executaMovimento(int linha, int coluna) {
		
		// trata MovimentoInvalidoException e IndiceInvalidoException caso movimento nao
		// for possivel
		try {
			isMovimentoValido(linha, coluna);
		} catch (MovimentoInvalidoException ex) {
			System.out.println(ex.getMessage());
			return false;
		} catch (IndiceInvalidoException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		int x = 0;
		int y = 0;
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {
				if(this.grid[i][j].getValor()==0) {
					x=i;
					y=j;
				}
			}
		}
		if (linha-1 == x) {
			// atribui o valor ao bloco em cima
			this.grid[linha - 1][coluna].setValor(this.grid[linha][coluna].getValor());
			// atribui 0 ao bloco anterior
			this.grid[linha][coluna].setValor(0);
			// seta validos
			setValidos();
		} else if (linha+1 == x) {
			this.grid[linha + 1][coluna].setValor(this.grid[linha][coluna].getValor());
			this.grid[linha][coluna].setValor(0);
			setValidos();
		} else if (coluna+1==y) {
			this.grid[linha][coluna + 1].setValor(this.grid[linha][coluna].getValor());
			this.grid[linha][coluna].setValor(0);
			setValidos();
		} else if (coluna-1 == y) {
			this.grid[linha][coluna - 1].setValor(this.grid[linha][coluna].getValor());
			this.grid[linha][coluna].setValor(0);
			setValidos();
		}

		return true;

	}

	// seta true nas casas que podem ser movidas de acordo com a posicao do zero
	public final void setValidos() {
		int j;
		// percorre a matriz
		for (int i = 0; i < this.grid.length; i++) {
			for (j = 0; j < this.grid.length; j++) {
				this.grid[i][j].setValido(false);
			}
		}
		for (int i = 0; i < this.grid.length; i++) {

			for (j = 0; j < this.grid.length; j++) {
				// acha o 0 e seta true nas pecas que podem ser movidas ao seu redor
				if (this.grid[i][j].getValor() == 0) {
					// verifica se esta dentro dos limites da matriz e se estiver,seta true na casa
					// de baixo
					if (i + 1 < this.grid.length) {
						this.grid[i + 1][j].setValido(true);
					}
					if (i - 1 >= 0) {
						this.grid[i - 1][j].setValido(true);
					}
					if (j + 1 < this.grid.length) {
						this.grid[i][j + 1].setValido(true);
					}
					if (j - 1 >= 0) {
						this.grid[i][j - 1].setValido(true);
					}
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

	// metodo para embaralhar a grid
	public void geraTabuleiro() {
		// cria obejto randomico
		Random random = new Random();
		// variaveis temporarias para manipulacao dos dados
		int randomico;
		int y[], z[];
		int contador;
		int contador1;

		// rotulo para sair do loop
		rotulo: while (true) {
			// faz mil embaralhamentos vezes o tamanho da grid
			for (int u = 0; u < 1000 * this.grid.length; u++) {
				contador = 0;
				contador1 = 0;
				// joga em contador quantos blocos validos existem na grid
				for (int i = 0; i < this.grid.length; i++) {
					for (int j = 0; j < this.grid.length; j++) {
						if (this.grid[i][j].isValido()) {
							contador++;
						}
					}
				}

				// cria dois arrays para armazenar as coordenadas dos blocos validos
				y = new int[contador];
				z = new int[contador];

				// joga as corrdenadas validas nos arrays
				for (int i = 0; i < this.grid.length; i++) {
					for (int j = 0; j < this.grid.length; j++) {
						if (this.grid[i][j].isValido()) {
							y[contador1] = i;
							z[contador1] = j;
							contador1++;
						}
					}
				}

			

				randomico = random.nextInt(contador);
				executaMovimento(y[randomico], z[randomico]);

					}
				

			

			// se nao estiver ordenado para o loop
			if (!isTabuleiroOrdenado()) {
				break rotulo;
			}
		}

	}

	

	// metodo que verifica se o movimento e valido
	public boolean isMovimentoValido(int linha, int coluna)
			throws MovimentoInvalidoException, IndiceInvalidoException {
		// verifica se esta dentro dos limites da grid
		if (linha < this.grid.length && linha >= 0 && coluna < this.grid.length && coluna >= 0) {
			// verifica se o bloco e valido para mover
			if (this.grid[linha][coluna].isValido()) {
				return true;
			} else {
				// se nao for valido para mover gera a mensagem do erro
				throw new MovimentoInvalidoException("Movimento Inválido.");
			}
		} else {
			// se nao estiver dentro dos limites da grid gera a mensagem do erro
			throw new IndiceInvalidoException("A casa que você deseja movimentar não existe.\n");
		}

	}
}
