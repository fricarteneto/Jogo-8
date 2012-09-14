package entidades;

public class Estado {

	private Peca[][] stateInicial;
	private Peca[][] stateFinal;
	private int funcaoAvaliacao;
	private int custoEstado;
	private int funcaoHeuristica;
	
	public Peca[][] getState() {
		return stateInicial;
	}
	public void setState(Peca[][] state) {
		this.stateInicial = state;
	}
	public Peca[][] getStateFinal() {
		return stateFinal;
	}
	public void setStateFinal(Peca[][] stateFinal) {
		this.stateFinal = stateFinal;
	}
	public int getFuncaoAvaliacao() {
		return funcaoAvaliacao;
	}
	public void setFuncaoAvaliacao() {
		this.funcaoAvaliacao = this.custoEstado + this.funcaoHeuristica;
	}
	public int getCustoEstado() {
		return custoEstado;
	}
	public void setCustoEstado(int custoEstado) {
		this.custoEstado = custoEstado;
	}
	public int getFuncaoHeuristica() {
		return calcularHeuristica();
	}
	public void setFuncaoHeuristica() {
		this.funcaoHeuristica = calcularHeuristica();
	}
	
	private int calcularHeuristica(){
		
		int distManhattan = 0;
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(stateInicial[i][j].getValor() != stateFinal[i][j].getValor()){
					distManhattan += capturaDistancia(i,j, stateInicial[i][j].getValor());
				}
			}
		}
		
		return distManhattan;
	}
	
	private int capturaDistancia(int l, int c, int valor) {
		// TODO Auto-generated method stub
		int linha = 0;
		int coluna = 0;
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(valor == stateFinal[i][j].getValor()){
					linha = i;
					coluna = j;
					break;
				}
			}
		}
		
		return Math.abs(l - linha) + Math.abs(c - coluna);
	}
	
}
