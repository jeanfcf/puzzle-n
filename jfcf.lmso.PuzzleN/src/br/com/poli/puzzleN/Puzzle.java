package br.com.poli.puzzleN;

import java.util.Calendar;

public class Puzzle {

	private Jogador jogador;
	private Tabuleiro gridPuzzle;
	private int quantidadeMovimento;
	private CalculaScore score;
	private boolean venceu;
	private Calendar tempo;
	private Dificuldade dificuldade;
	//par ordenado para armazenar a posicao do 0
	private ParOrdenado p0;
	//par ordenado para armazenar a posicao certa do numero
	private ParOrdenado pCerta;
	//par ordenado para armazenar a posicao do numero
	private ParOrdenado pn;

	// construtor que inicializa com o nome e dificuldade
	public Puzzle(Jogador jogador, Dificuldade dificuldade) {
		this.jogador = jogador;
		this.dificuldade = dificuldade;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Tabuleiro getGridPuzzle() {
		return gridPuzzle;
	}

	public void setGridPuzzle(Tabuleiro gridPuzzle) {
		this.gridPuzzle = gridPuzzle;
	}

	public int getQuantidadeMovimento() {
		return quantidadeMovimento;
	}

	public void setQuantidadeMovimento(int quantidadeMovimento) {
		this.quantidadeMovimento = quantidadeMovimento;
	}

	public int getScore() {
		return score.pontos(this);
	}

	public void setScore(CalculaScore score) {
		this.score = score;
	}

	public boolean isVenceu() {
		return venceu;
	}

	public void setVenceu(boolean venceu) {
		this.venceu = venceu;
	}

	public Calendar getTempo() {
		return tempo;
	}

	public void setTempo(Calendar tempo) {
		this.tempo = tempo;
	}

	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

	// metodo para impedir movimentos apos vencer lanca MovimentoInvalidoException
	public boolean MovimentoPosVencer() throws MovimentoInvalidoException {
		if (this.venceu) {
			throw new MovimentoInvalidoException("InvalidMove: You've already won");
		} else {
			return true;
		}
	}

	// metodo que vai saber se o jogo foi finalizado
	public boolean isFimDeJogo() {
		return this.getGridPuzzle().isTabuleiroOrdenado();
	}

	// metodo responsavel por inicializar a partida
	public void iniciaPartida() {
		this.quantidadeMovimento = 0;
		this.tempo = Calendar.getInstance();
		this.venceu = false;
		this.gridPuzzle = new Tabuleiro(this.dificuldade);
		this.getGridPuzzle().geraTabuleiro();
	}

	// metodo responsavel por imprimir a matriz para testes
	public void imprimeGrid() {
		for (int i = 0; i < getGridPuzzle().getTamanhoGrid(); i++) {
			for (int j = 0; j < getGridPuzzle().getTamanhoGrid(); j++) {
				System.out.print(getGridPuzzle().getValorGrid(i, j) + "  ");
				if (getGridPuzzle().getValorGrid(i, j) < 10) {
					System.out.print(" ");
				}
				if (getGridPuzzle().getValorGrid(i, j) < 100) {
					System.out.print(" ");
				}
				if (j == getGridPuzzle().getTamanhoGrid() - 1) {
					System.out.println();
					System.out.println();
				}
			}
		}
	}

	// metodo responsavel por imprimir a matriz para testes
	public void imprimeGridValido() {
		for (int i = 0; i < getGridPuzzle().getTamanhoGrid(); i++) {
			for (int j = 0; j < getGridPuzzle().getTamanhoGrid(); j++) {
				System.out.print(getGridPuzzle().getPosicaoGrid(i, j).isValido() + " ");
				if (getGridPuzzle().getPosicaoGrid(i, j).isValido()) {
					System.out.print(" ");
				}
				if (j == getGridPuzzle().getTamanhoGrid() - 1) {
					System.out.println();
				}
			}
		}
	}

	// metodo para fazer o movimento e onde e tratado a excecao caso tentar mover
	// apos ganhar
	public void fazMovimento(int linha, int coluna) {
		
	
		try {
			if (MovimentoPosVencer()) {

				if (this.getGridPuzzle().executaMovimento(linha, coluna)) {
					this.quantidadeMovimento++;
					
					// se quiser que printe os movimentos no console
					imprimeGrid();
					System.out.println("=============================================================");
				}
				vencer();
			}
		} catch (MovimentoInvalidoException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// metodo para saber se venceu
	public void vencer() {
		if (this.getGridPuzzle().isTabuleiroOrdenado()) {
			this.venceu = true;
		}
	}

	public void resolveTabuleiro(Dificuldade dificuldade) throws TempoExcedidoException {
		long start = System.currentTimeMillis();
		System.out.println("======================RESOLVENDO============================");
		while(!venceu) {
			executaMovimentoAuto();
			
			if(System.currentTimeMillis()-start >10000) {
				throw new TempoExcedidoException("ERRO");
			}
			
		}
		System.out.println("===================RESOLVIDO================================");
		
	}

	
	// acha um numero e retorna a um par ordenado com as posicoes do mesmo
	public ParOrdenado verificaNumero(int n) {
		int x = -1, y = -1;
		rotulo: for (int i = 0; i < this.getGridPuzzle().getTamanhoGrid(); i++) {
			for (int j = 0; j < this.getGridPuzzle().getTamanhoGrid(); j++) {
				if (this.gridPuzzle.getValorGrid(i, j) == n) {
					x = i;
					y = j;
					break rotulo;
				}
			}
		}

		return new ParOrdenado(x, y);
	}

	// verifica qual a posicao certa da casa e retorna a um par ordenado com as
	// coordenadas da mesma
	public ParOrdenado verificaCerta(int valorCasa) {
		for (int i = 0; i < getGridPuzzle().getTamanhoGrid(); i++) {
			for (int j = 0; j < getGridPuzzle().getTamanhoGrid(); j++) {
				if (getGridPuzzle().getPosicaoCerta(i, j) == valorCasa) {
					return new ParOrdenado(i, j);
				}
			}
		}
		return null;
	}
	
	//em ordem, acha o proximo numero que ainda nao esta na sua posicao certa
	public int acharNumero() {
		// variavel que armazena a diferenca entre linhas
		int contador = 1;
		// for para percorrer a grid verificando se est? em ordem
		for (int i = 0; i < getGridPuzzle().getTamanhoGrid(); i++) {
			for (int j = 0; j < getGridPuzzle().getTamanhoGrid(); j++) {
				if (i == j && i == getGridPuzzle().getTamanhoGrid() - 1) {
					// se nao for zero na ultima casa retorna a falso
					if (getGridPuzzle().getValorGrid(i, j) != 0) {
						return 0;
					}
					// se nao for o numero apropriado para casa retorna a falso
				} else if (getGridPuzzle().getValorGrid(i, j) != i + j + contador) {
					return i + j + contador;
				}
			}
			contador += (getGridPuzzle().getTamanhoGrid() - 1);
		}
		return -1;
	}

	// acha o proximo numero caso seja uma das duas linhas finais da grid
	public int achaNumeroLinhaBaixo() {
		for (int j = 0; j < getGridPuzzle().getTamanhoGrid(); j++) {
			for (int i = getGridPuzzle().getTamanhoGrid() - 2; i < getGridPuzzle()
					.getTamanhoGrid(); i++) {

				if (!(getGridPuzzle().getPosicaoCerta(i, j) == getGridPuzzle().getValorGrid(i, j))) {
					return getGridPuzzle().getPosicaoCerta(i, j);
				}

			}
		}
		return -1;
	}

	// percorre a diagonal da casa certa e verifica se o numero esta na mesma
	public boolean percorreDiagonal(ParOrdenado casaCerta) {
		int contador = 0;

		while (casaCerta.getJ() + contador < getGridPuzzle().getTamanhoGrid()
				&& casaCerta.getI() + contador < getGridPuzzle().getTamanhoGrid()) {

			if (getGridPuzzle().getPosicaoGrid(casaCerta.getI() + contador, casaCerta.getJ() + contador)
					.getValor() == acharNumero()) {
				return true;
			}

			contador++;
		}
		return false;

	}
	
	// acha a ultima posicao da diagonal da casa certa
	public ParOrdenado achaDiagonal(ParOrdenado casaCerta) {

		int contador = 0;

		while (casaCerta.getJ() + contador < getGridPuzzle().getTamanhoGrid()
				&& casaCerta.getI() + contador < getGridPuzzle().getTamanhoGrid()) {

			contador++;
		}

		return new ParOrdenado(casaCerta.getI() + contador - 1, casaCerta.getJ() + contador - 1);

	}


	// coloca o zero ao lado do numero
	public void colocaEmbaixo() {
		if (p0.getJ() == pn.getJ()) {// se nao estiver na diagonal e o 0 estiver na mesma coluna que o numero
			if (!(pn.getI() == getGridPuzzle().getTamanhoGrid() - 1)) {// se o numero nao estiver na ultima
																				// linha
				if (p0.getI() > pn.getI()) {
					while (p0.getI() > pn.getI() + 1) {
						p0.setI(p0.getI() - 1);
						fazMovimento(p0.getI(), p0.getJ());
						
					}
				} else {
					while (p0.getI() < pn.getI() - 1) {
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
					if (pn.getJ() != getGridPuzzle().getTamanhoGrid() - 1) {
						p0.setJ(p0.getJ() + 1);
						fazMovimento(p0.getI(), p0.getJ());
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
						p0.setJ(p0.getJ() - 1);
						fazMovimento(p0.getI(), p0.getJ());
					} else {
						p0.setJ(p0.getJ() - 1);
						fazMovimento(p0.getI(), p0.getJ());
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
						p0.setJ(p0.getJ() + 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
				}
			} else {// se o numero estiver na ultima linha
				while (p0.getI() < pn.getI() - 1) {
					p0.setI(p0.getI() + 1);
					fazMovimento(p0.getI(), p0.getJ());
				}
			}
		} else {// se nao estiver na diagonal e nao estiver na mesma coluna
			if (p0.getI() != pn.getI()) {// se o zero nao estiver na mesma linha do numero
				if (pn.getI() != getGridPuzzle().getTamanhoGrid() - 1) {// se o numero nao estiver na ultima
																				// linha
					while (p0.getI() < pn.getI() + 1) {
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
					while (p0.getI() > pn.getI() + 1) {
						p0.setI(p0.getI() - 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
					while (p0.getJ() < pn.getJ()) {
						p0.setJ(p0.getJ() + 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
					while (p0.getJ() > pn.getJ()) {
						p0.setJ(p0.getJ() - 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
				} else {// se o numero estiver na ultima linha
					while (p0.getI() < pn.getI() - 1) {
						p0.setI(p0.getI() + 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
					while (p0.getJ() < pn.getJ()) {
						p0.setJ(p0.getJ() + 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
					while (p0.getJ() > pn.getJ()) {
						p0.setJ(p0.getJ() - 1);
						fazMovimento(p0.getI(), p0.getJ());
					}
				}
			} else {// se o 0 estiver na linha do numero
				while (p0.getJ() < pn.getJ() - 1) {
					p0.setJ(p0.getJ() + 1);
					fazMovimento(p0.getI(), p0.getJ());
				}
				while (p0.getJ() > pn.getJ() + 1) {
					p0.setJ(p0.getJ() - 1);
					fazMovimento(p0.getI(), p0.getJ());
				}
				if (pn.getI() != getGridPuzzle().getTamanhoGrid() - 1) {// se o numero nao estiver na ultima
																				// linha
					p0.setI(p0.getI() + 1);
					fazMovimento(p0.getI(), p0.getJ());
				} else {// se o numero estiver na ultima linha
					p0.setI(p0.getI() - 1);
					fazMovimento(p0.getI(), p0.getJ());
				}
				if (p0.getJ() < pn.getJ()) {
					p0.setJ(p0.getJ() + 1);
					fazMovimento(p0.getI(), p0.getJ());
				} else {
					p0.setJ(p0.getJ() - 1);
					fazMovimento(p0.getI(), p0.getJ());
				}
			}
		}

	}
	
	// leva o numero para a ultima posicao da diagonal da casa certa
		public void levaDiagonal() {
			// ultima posicao da diagonal certa
			ParOrdenado pun = achaDiagonal(pCerta);

			// enquanto o numero nao estiver na diagonal certa
			while (!percorreDiagonal(pCerta)) {
				// se o numero estiver na ultima linha entra
				if (pn.getI() == getGridPuzzle().getTamanhoGrid() - 1) {
					// se o numero estiver na mesma linha de pun
					if (pn.getI() == pun.getI()) {
						while (pn.getJ() != pun.getJ()) {
							if (pn.getJ() < pun.getJ()) {
								fazMovimento(p0.getI(), p0.getJ() + 1);
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI() + 1, p0.getJ());
								p0.setI(p0.getI() + 1);
								fazMovimento(pn.getI(), pn.getJ());
								p0.setJ(pn.getJ());
								pn.setJ(pn.getJ() + 1);
								colocaEmbaixo();
								p0.setIJ(pn.getI() - 1, pn.getJ());
							} else {
								fazMovimento(p0.getI(), p0.getJ() - 1);
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI() + 1, p0.getJ());
								p0.setI(p0.getI() + 1);
								fazMovimento(pn.getI(), pn.getJ());
								p0.setJ(pn.getJ());
								pn.setJ(pn.getJ() - 1);
								colocaEmbaixo();
								p0.setIJ(pn.getI() - 1, pn.getJ());
							}
						}
						// se nao estiver na mesma linha que pun
					} else {
						fazMovimento(pn.getI(), pn.getJ());
						pn.setI(p0.getI());
						p0.setI(pn.getI() + 1);
					}
					// se nao estiver na ultima linha
				} else {
					// estiver na mesma linha de pun
					if (pn.getI() == pun.getI()) {
						while (pn.getJ() != pun.getJ()) {
							if (pn.getJ() < pun.getJ()) {
								fazMovimento(p0.getI(), p0.getJ() + 1);
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI() - 1, p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(pn.getI(), pn.getJ());
								p0.setIJ(pn.getI(), pn.getJ());
								pn.setJ(pn.getJ() + 1);
								colocaEmbaixo();
								p0.setIJ(pn.getI() + 1, pn.getJ());
							} else {
								fazMovimento(p0.getI(), p0.getJ() - 1);
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI() - 1, p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(pn.getI(), pn.getJ());
								p0.setIJ(pn.getI(), pn.getJ());
								pn.setJ(pn.getJ() - 1);
								colocaEmbaixo();
								p0.setIJ(pn.getI() + 1, pn.getJ());
							}
						}
						// nao estiver na mesma linha que pun
					} else {
						// se pn estiver na borda direita da grid
						if (pn.getJ() == getGridPuzzle().getTamanhoGrid() - 1) {
							// se pn for menor que pun
							while (pn.getI() < pun.getI()) {
								fazMovimento(pn.getI(), pn.getJ());
								p0.setIJ(pn.getI(), pn.getJ());
								pn.setI(pn.getI() + 1);
								if (pn.getI() == pun.getI()) {
									break;
								}
								fazMovimento(p0.getI(), p0.getJ() - 1);
								p0.setJ(p0.getJ() - 1);
								if (pn.getI() == pun.getI()) {
									break;
								}
								fazMovimento(p0.getI() + 1, p0.getJ());
								p0.setI(p0.getI() + 1);
								if (pn.getI() == pun.getI()) {
									break;
								}
								fazMovimento(p0.getI() + 1, p0.getJ());
								p0.setI(p0.getI() + 1);
								if (pn.getI() == pun.getI()) {
									break;
								}
								fazMovimento(p0.getI(), p0.getJ() + 1);
								p0.setJ(p0.getJ() + 1);
								if (pn.getI() == pun.getI()) {
									break;
								}
							}
							colocaEmbaixo();
							// se pn for maior que pun
							while (pn.getI() > pun.getI()) {
								fazMovimento(p0.getI(), p0.getJ() - 1);
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI() - 1, p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI() - 1, p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ() + 1);
								p0.setJ(p0.getJ() + 1);
								fazMovimento(pn.getI(), pn.getJ());
								p0.setIJ(pn.getI(), pn.getJ());
								pn.setI(pn.getI() - 1);
							}
							colocaEmbaixo();
							// se nao estiver na borda da grid
						} else {
							// se for menor que pun
							while (pn.getI() < pun.getI()) {
								fazMovimento(pn.getI(), pn.getJ());
								p0.setIJ(pn.getI(), pn.getJ());
								pn.setI(pn.getI() + 1);
								if (pn.getI() == pun.getI()) {
									break;
								}
								fazMovimento(p0.getI(), p0.getJ() + 1);
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI() + 1, p0.getJ());
								p0.setI(p0.getI() + 1);
								fazMovimento(p0.getI() + 1, p0.getJ());
								p0.setI(p0.getI() + 1);
								fazMovimento(p0.getI(), p0.getJ() - 1);
								p0.setJ(p0.getJ() - 1);
							}
							colocaEmbaixo();
							if (pn.getI() == getGridPuzzle().getTamanhoGrid() - 1) {
								colocaEmbaixo();
								p0.setIJ(pn.getI() - 1, pn.getJ());
							}
							while (pn.getI() > pun.getI()) {
								fazMovimento(p0.getI(), p0.getJ() + 1);
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI() - 1, p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI() - 1, p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ() - 1);
								p0.setJ(p0.getJ() - 1);
								fazMovimento(pn.getI(), pn.getJ());
								p0.setIJ(pn.getI(), pn.getJ());
								pn.setI(pn.getI() - 1);
							}
							if (pn.getI() == getGridPuzzle().getTamanhoGrid() - 1) {
								colocaEmbaixo();
								p0.setIJ(pn.getI() - 1, pn.getJ());
							}
						}
					}
				}
			}
		}

	// leva o numero para embaixo da sua casa certa, caso a posicao do mesmo esteja
	// na ultima coluna da grid
	public void levaEmbaixoQuina() {
		// se estiver na ultima linha
		if (pn.getI() == getGridPuzzle().getTamanhoGrid() - 1) {

			while (pn.getJ() < getGridPuzzle().getTamanhoGrid() - 1) {
				fazMovimento(p0.getI(), p0.getJ() + 1);
				p0.setJ(p0.getJ() + 1);
				fazMovimento(p0.getI() + 1, p0.getJ());
				p0.setI(p0.getI() + 1);
				fazMovimento(pn.getI(), pn.getJ());
				pn.setIJ(p0.getI(), p0.getJ());
				p0.setIJ(pn.getI(), pn.getJ() - 1);
				colocaEmbaixo();
				p0.setIJ(pn.getI() - 1, pn.getJ());
			}
			fazMovimento(pn.getI(), pn.getJ());
			pn.setIJ(p0.getI(), p0.getJ());
			p0.setIJ(pn.getI() + 1, pn.getJ());
			while (pn.getI() > pCerta.getI() + 1) {
				fazMovimento(p0.getI(), p0.getJ() - 1);
				p0.setJ(p0.getJ() - 1);
				fazMovimento(p0.getI() - 1, p0.getJ());
				p0.setI(p0.getI() - 1);
				fazMovimento(p0.getI() - 1, p0.getJ());
				p0.setI(p0.getI() - 1);
				fazMovimento(p0.getI(), p0.getJ() + 1);
				p0.setJ(p0.getJ() + 1);
				fazMovimento(pn.getI(), pn.getJ());
				p0.setIJ(pn.getI(), pn.getJ());
				pn.setI(pn.getI() - 1);
			}
			// se nao estiver na ultima linha
		} else {

			while (pn.getJ() < getGridPuzzle().getTamanhoGrid() - 1) {
				fazMovimento(p0.getI(), p0.getJ() + 1);
				p0.setJ(p0.getJ() + 1);
				fazMovimento(p0.getI() - 1, p0.getJ());
				p0.setI(p0.getI() - 1);
				fazMovimento(pn.getI(), pn.getJ());
				pn.setIJ(p0.getI(), p0.getJ());
				p0.setIJ(pn.getI(), pn.getJ() - 1);
				colocaEmbaixo();
				p0.setIJ(pn.getI() + 1, pn.getJ());
			}

			while (pn.getI() > pCerta.getI() + 1) {
				fazMovimento(p0.getI(), p0.getJ() - 1);
				p0.setJ(p0.getJ() - 1);
				fazMovimento(p0.getI() - 1, p0.getJ());
				p0.setI(p0.getI() - 1);
				fazMovimento(p0.getI() - 1, p0.getJ());
				p0.setI(p0.getI() - 1);
				fazMovimento(p0.getI(), p0.getJ() + 1);
				p0.setJ(p0.getJ() + 1);
				fazMovimento(pn.getI(), pn.getJ());
				p0.setIJ(pn.getI(), pn.getJ());
				pn.setI(pn.getI() - 1);
			}

		}
	}

	// leva o numero para a posicao certa da casa
	public void executaMovimentoAuto() {
		p0 = verificaNumero(0);

		if (acharNumero() > getGridPuzzle().getPosicaoCerta(getGridPuzzle().getTamanhoGrid() - 3,
				getGridPuzzle().getTamanhoGrid() - 1)) {

			pn = verificaNumero(achaNumeroLinhaBaixo());
			pCerta = verificaCerta(achaNumeroLinhaBaixo());

		} else {
			pn = verificaNumero(acharNumero());
			pCerta = verificaCerta(acharNumero());
		}

		while (pn.getI() != pCerta.getI() || pn.getJ() != pCerta.getJ()) {

			if (pCerta.getI() == getGridPuzzle().getTamanhoGrid() - 1
					&& pCerta.getJ() == getGridPuzzle().getTamanhoGrid() - 2) {

				fazMovimento(pn.getI(), pn.getJ());
				p0.setIJ(pn.getI(), pn.getJ());
				pn.setIJ(p0.getI(), p0.getJ() + 1);
				break;

			} else if (pCerta.getI() == getGridPuzzle().getTamanhoGrid() - 2) {
				colocaEmbaixo();
				if (pn.getI() > pCerta.getI()) {
					fazMovimento(pn.getI(), pn.getJ());
					p0.setIJ(pn.getI(), pn.getJ());
					pn.setIJ(p0.getI() - 1, p0.getJ());
				}
				while (pn.getJ() > pCerta.getJ()) {

					fazMovimento(p0.getI(), p0.getJ() - 1);
					p0.setJ(p0.getJ() - 1);

					if (pn.getJ() == pCerta.getJ()) {
						break;
					}

					fazMovimento(p0.getI() - 1, p0.getJ());
					p0.setI(p0.getI() - 1);
					if (pn.getJ() == pCerta.getJ()) {
						break;
					}

					fazMovimento(pn.getI(), pn.getJ());
					p0.setIJ(pn.getI(), pn.getJ());
					pn.setIJ(p0.getI(), pn.getJ() - 1);
					if (pn.getJ() == pCerta.getJ()) {
						break;
					}

					fazMovimento(p0.getI() + 1, p0.getJ());
					p0.setI(p0.getI() + 1);
					if (pn.getJ() == pCerta.getJ()) {
						break;
					}

					fazMovimento(p0.getI(), p0.getJ() - 1);
					p0.setJ(p0.getJ() - 1);
					if (pn.getJ() == pCerta.getJ()) {
						break;
					}

				}

			} else if (pCerta.getI() == getGridPuzzle().getTamanhoGrid() - 1) {
				if (p0.getI() == pCerta.getI() && p0.getJ() == pCerta.getJ() && pn.getI() == pCerta.getI()
						&& pn.getJ() == pCerta.getJ() + 1) {
					fazMovimento(pn.getI(), pn.getJ());
					pn.setIJ(p0.getI(), p0.getJ());
					p0.setIJ(pn.getI() - 1, pn.getJ());
				} else {
					colocaEmbaixo();
					if (pn.getI() < pCerta.getI()) {
						fazMovimento(pn.getI(), pn.getJ());
						p0.setIJ(pn.getI(), pn.getJ());
						pn.setIJ(p0.getI() + 1, p0.getJ());
					}

					while (pn.getJ() > pCerta.getJ() + 1) {

						fazMovimento(p0.getI(), p0.getJ() - 1);
						p0.setJ(p0.getJ() - 1);

						fazMovimento(p0.getI() + 1, p0.getJ());
						p0.setI(p0.getI() + 1);

						fazMovimento(pn.getI(), pn.getJ());
						p0.setIJ(pn.getI(), pn.getJ());
						pn.setIJ(p0.getI(), pn.getJ() - 1);

						fazMovimento(p0.getI() - 1, p0.getJ());
						p0.setI(p0.getI() - 1);

						fazMovimento(p0.getI(), p0.getJ() - 1);
						p0.setJ(p0.getJ() - 1);

					}

					fazMovimento(p0.getI(), p0.getJ() + 1);
					p0.setJ(p0.getJ() + 1);

					fazMovimento(p0.getI() + 1, p0.getJ());
					p0.setI(p0.getI() + 1);

					fazMovimento(pn.getI(), pn.getJ());
					p0.setIJ(pn.getI(), pn.getJ());
					pn.setIJ(p0.getI(), pn.getJ() + 1);

					fazMovimento(p0.getI(), p0.getJ() - 1);
					p0.setJ(p0.getJ() - 1);

					fazMovimento(p0.getI() - 1, p0.getJ());
					p0.setI(p0.getI() - 1);

					fazMovimento(p0.getI(), p0.getJ() + 1);
					p0.setJ(p0.getJ() + 1);
					fazMovimento(p0.getI() + 1, p0.getJ());
					p0.setI(p0.getI() + 1);
					fazMovimento(p0.getI(), p0.getJ() + 1);
					p0.setJ(p0.getJ() + 1);
					fazMovimento(p0.getI() - 1, p0.getJ());
					p0.setI(p0.getI() - 1);
					fazMovimento(p0.getI(), p0.getJ() - 1);
					p0.setJ(p0.getJ() - 1);
					fazMovimento(p0.getI(), p0.getJ() - 1);
					p0.setJ(p0.getJ() - 1);

					fazMovimento(p0.getI() + 1, p0.getJ());
					p0.setI(p0.getI() + 1);
					fazMovimento(p0.getI(), p0.getJ() + 1);
					p0.setJ(p0.getJ() + 1);

				}
				break;

			} else {

				// se o numero nao tiver na diagonal certa entra
				if (!percorreDiagonal(pCerta)) {
					if (pCerta.getJ() == getGridPuzzle().getTamanhoGrid() - 1) {

						if (p0.getI() == pCerta.getI() && p0.getJ() == pCerta.getJ() && pn.getI() == pCerta.getI() + 1
								&& pn.getJ() == pCerta.getJ()) {
							fazMovimento(pn.getI(), pn.getJ());
							pn.setIJ(p0.getI(), p0.getJ());
							p0.setIJ(pn.getI() - 1, pn.getJ());
						} else {
							colocaEmbaixo();
							levaEmbaixoQuina();
							fazMovimento(pn.getI(), pn.getJ());
							pn.setIJ(p0.getI(), p0.getJ());
							p0.setIJ(pn.getI() - 1, pn.getJ());
							fazMovimento(p0.getI() - 1, p0.getJ());
							p0.setI(p0.getI() - 1);
							fazMovimento(p0.getI(), p0.getJ() - 1);
							p0.setJ(p0.getJ() - 1);
							fazMovimento(p0.getI() + 1, p0.getJ());
							p0.setI(p0.getI() + 1);
							fazMovimento(p0.getI(), p0.getJ() + 1);
							p0.setJ(p0.getJ() + 1);
							fazMovimento(pn.getI(), pn.getJ());
							pn.setIJ(p0.getI(), p0.getJ());
							p0.setIJ(pn.getI() + 1, pn.getJ());
							fazMovimento(p0.getI(), p0.getJ() - 1);
							p0.setJ(p0.getJ() - 1);
							fazMovimento(p0.getI() - 1, p0.getJ());
							p0.setI(p0.getI() - 1);
							fazMovimento(p0.getI() - 1, p0.getJ());
							p0.setI(p0.getI() - 1);
							fazMovimento(p0.getI(), p0.getJ() + 1);
							p0.setJ(p0.getJ() + 1);
							fazMovimento(pn.getI(), pn.getJ());
							pn.setIJ(p0.getI(), p0.getJ());
							p0.setIJ(pn.getI() + 1, pn.getJ());

						}

					} else {

						colocaEmbaixo();
						levaDiagonal();
					}
					// se o numero estiver na diagonal
				} else {
					// se o 0 nao estiver na mesma coluna que o numero, entra
					if (!(p0.getJ() == pn.getJ())) {
						if (pn.getI() != getGridPuzzle().getTamanhoGrid() - 1) {
							while (p0.getI() > pn.getI() + 1) {
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							while (p0.getI() < pn.getI() + 1) {
								p0.setI(p0.getI() + 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							// joga o 0 pra cima do numero
							while (p0.getJ() < pn.getJ()) {
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							while (p0.getJ() > pn.getJ()) {
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							if (pn.getJ() != getGridPuzzle().getTamanhoGrid() - 1) {
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI(), p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ());
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI(), p0.getJ());
							} else {
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI(), p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ());
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ());
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
						} else {
							// joga o 0 pra cima do numero
							while (p0.getJ() < pn.getJ() - 1) {
								p0.setJ(p0.getJ() + 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							while (p0.getJ() > pn.getJ() + 1) {
								p0.setJ(p0.getJ() - 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							while (p0.getI() < pn.getI()) {
								p0.setI(p0.getI() + 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							p0.setI(p0.getI() - 1);
							fazMovimento(p0.getI(), p0.getJ());
							if (p0.getJ() < pn.getJ()) {
								p0.setJ(p0.getJ() + 1);
							}
							if (p0.getJ() > pn.getJ()) {
								p0.setJ(p0.getJ() - 1);
							}
							fazMovimento(p0.getI(), p0.getJ());
						}
						// se o 0 estiver na mesma coluna que o numero
					} else {
						// joga o 0 pra cima do numero
						if (p0.getI() > pn.getI()) {
							while (p0.getI() - 1 > pn.getI()) {
								p0.setI(p0.getI() - 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
							p0.setJ(p0.getJ() - 1);
							fazMovimento(p0.getI(), p0.getJ());
							p0.setI(p0.getI() - 1);
							fazMovimento(p0.getI(), p0.getJ());
							p0.setI(p0.getI() - 1);
							fazMovimento(p0.getI(), p0.getJ());
							p0.setJ(p0.getJ() + 1);
							fazMovimento(p0.getI(), p0.getJ());
						} else {
							while (p0.getI() < pn.getI() - 1) {
								p0.setI(p0.getI() + 1);
								fazMovimento(p0.getI(), p0.getJ());
							}
						}
					}
					// joga o numero pra posicao correta
					do {
						fazMovimento(pn.getI(), pn.getJ());
						p0.setIJ(pn.getI(), pn.getJ());
						pn.setIJ(pn.getI() - 1, pn.getJ());
						if (pn.getI() == pCerta.getI() && pn.getJ() == pCerta.getJ()) {
							break;
						}
						fazMovimento(p0.getI(), p0.getJ() - 1);
						p0.setIJ(p0.getI(), p0.getJ() - 1);
						fazMovimento(p0.getI() - 1, p0.getJ());
						p0.setIJ(p0.getI() - 1, p0.getJ());
						fazMovimento(pn.getI(), pn.getJ());
						p0.setIJ(pn.getI(), pn.getJ());
						pn.setIJ(pn.getI(), pn.getJ() - 1);
						if (pn.getI() == pCerta.getI() && pn.getJ() == pCerta.getJ()) {
							break;
						}
						fazMovimento(p0.getI() - 1, p0.getJ());
						p0.setIJ(p0.getI() - 1, p0.getJ());
						fazMovimento(p0.getI(), p0.getJ() - 1);
						p0.setIJ(p0.getI(), p0.getJ() - 1);
					} while (pn.getI() != pCerta.getI() && pn.getJ() != pCerta.getJ());
				}
			}

		}

	}

}

