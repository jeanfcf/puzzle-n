package br.com.poli.puzzleN;

public class ParOrdenado {

	public ParOrdenado(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	
	public ParOrdenado() {
	
	}
	
	private int i;
	private int j;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public void setIJ(int i, int j) {
		this.i = i;
		this.j = j;
	}
	

}
