package puzzleN;

import java.util.Calendar;

public class Puzzle {
	
	private Jogador jogador;
	private Tabuleiro gridPuzzle;
	private int quantidadeMovimento;
	private int score;
	private boolean venceu;
	private Calendar tempo;
	private Dificuldade dificuldade;
	
	//construtor que inicializa com o nome e dificuldade
	public Puzzle(Jogador jogador, Dificuldade dificuldade) {
		this.jogador = jogador;
		this.dificuldade = dificuldade;
	}
	
	//metodo que vai saber se 
	public boolean isFimDeJogo() {
		return this.gridPuzzle.isTabuleiroOrdenado();
	}

	public void iniciaPartida() {
		this.quantidadeMovimento = 0;
		this.tempo = Calendar.getInstance();
		this.venceu = false;
		this.gridPuzzle.geraTabuleiro(this.dificuldade);
		//this.gridPuzzle = new Tabuleiro(this.dificuldade);
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
		return score;
	}

	public void setScore(int score) {
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
}
