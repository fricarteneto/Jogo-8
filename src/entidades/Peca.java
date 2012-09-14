package entidades;

public class Peca {

	private int valor;
	private int posIni;
	private int posFim;
	
	public Peca() {
		// TODO Auto-generated constructor stub
	}
	
	public Peca(int valor){
		this.valor = valor;
	}

	public int getPosIni() {
		return posIni;
	}

	public void setPosIni(int posIni) {
		this.posIni = posIni;
	}

	public int getPosFim() {
		return posFim;
	}

	public void setPosFim(int posFim) {
		this.posFim = posFim;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public boolean posicaoCorreta(){
		if(posIni == posFim){
			return true;
		}else{
			return false;
		}
	}
}
