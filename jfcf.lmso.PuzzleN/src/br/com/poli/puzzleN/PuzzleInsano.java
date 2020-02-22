package br.com.poli.puzzleN;

import java.util.Calendar;

public class PuzzleInsano extends Puzzle {
	private int tamanho;

	public PuzzleInsano(Jogador jogador,int k)  {
		super(jogador, Dificuldade.INSANO);
		// cria um novo calculascore insano temporario e seta no score da superclasse
		CalculaScore temp = new CalculaInsano();
		this.setScore(temp);
		// trata o IndiceInvalidoException
		try {
			// verifica se o k recibo e valido e se for atribui a atributo tamanho
			if(isKvalido(k)) {
			this.tamanho = k;
			}
		} catch (IndiceInvalidoException ex) {
			// se a excecao for pega seta o tamanho padrao da grid insano 6 e printa a mensagem de erro
			this.tamanho = 6;
			System.out.println(ex.getMessage());
		}
	}
	
	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	// metodo para verificar se o k e valido
		public boolean isKvalido(int k) throws IndiceInvalidoException{
			// se k for maior ou igual a 5 ou maior ou igual a 1000 lanca uma excecao
			if (k <= 5 || k>=1000) {
				throw new IndiceInvalidoException("InvalidIndex InsanePuzzleSize Redirected to 6");
			}else {
				// se nao for retorna true
				return  true;
			}
		}
	
	// metodo para sobrescrever 
	@Override
	public void iniciaPartida() {
		this.setQuantidadeMovimento(0);
		this.setTempo(Calendar.getInstance());
		this.setVenceu(false);
		this.setGridPuzzle(new Tabuleiro(this.getDificuldade(),this.tamanho));
		this.getGridPuzzle().geraTabuleiro();
	}
	
}
