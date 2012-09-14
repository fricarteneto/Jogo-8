package entidades;

public class Tabuleiro {
	
	private final Peca peca1 = new Peca(1);
	private final Peca peca2 = new Peca(2);
	private final Peca peca3 = new Peca(3);
	private final Peca peca4 = new Peca(4);
	private final Peca peca5 = new Peca(5);
	private final Peca peca6 = new Peca(6);
	private final Peca peca7 = new Peca(7);
	private final Peca peca8 = new Peca(8);
	private final Peca pecaBranca = new Peca(0);
	
	
	//private final Peca[][] estadoInicial = { {peca4, peca7, peca3}, {peca2, peca1, peca6}, {peca5, peca8, pecaBranca} };
	private final Peca[][] estadoInicial = { {peca1, peca2, peca3}, {peca4, pecaBranca, peca6}, {peca7, peca5, peca8} };
	private final Peca[][] estadoObjetivo = { {peca1, peca2, peca3}, {peca4, peca5, peca6}, {peca7, peca8, pecaBranca} };
	private Peca[][] estadoAtual;
	private int numNivel = 0;
	
	public Tabuleiro() {
		// TODO Auto-generated constructor stub
		estadoAtual = estadoInicial;
	}
	
	public Peca[][] movimentarPecas(char sentido){
		
		Peca[][] saida = null;
		switch(sentido){
			
			case 'c':
				saida = moveCima();
				break;
			case 'b':
				saida = moveBaixo();
				break;
			case 'd':
				saida = moveDireita();
				break;
			case 'e':	
				saida = moveEsquerda();
				break;
		}
		
		return saida;
	}
	
	private Peca[][] moveEsquerda() {
		
		Peca[][] estadoAux = new Peca[3][3];
		estadoAux = getEstadoAtual();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(estadoAux[i][j].getValor() == 0 && j != 0){
					Peca aux = estadoAux[i][j];
					estadoAux[i][j] = estadoAux[i][j-1];
					estadoAux[i][j-1] = aux;
				}
			}
		}
		
		return estadoAux;
	}

	private Peca[][] moveDireita() {
		
		Peca[][] estadoAux = new Peca[3][3];
		estadoAux = getEstadoAtual();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(estadoAux[i][j].getValor() == 0  && j != 2){
					Peca aux = estadoAux[i][j];
					estadoAux[i][j] = estadoAux[i][j+1];
					estadoAux[i][j+1] = aux;
				}
			}
		}
		
		return estadoAux;
	}

	private Peca[][] moveBaixo() {
		
		Peca[][] estadoAux = new Peca[3][3];
		estadoAux = getEstadoAtual();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(estadoAux[i][j].getValor() == 0  && i != 2){
					Peca aux = estadoAux[i][j];
					estadoAux[i][j] = estadoAux[i+1][j];
					estadoAux[i+1][j] = aux;
				}
			}
		}
		
		return estadoAux;
	}

	private Peca[][] moveCima() {
		
		Peca[][] estadoAux = new Peca[3][3];
		estadoAux = getEstadoAtual();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(estadoAux[i][j].getValor() == 0  && i != 0){
					Peca aux = estadoAux[i][j];
					estadoAux[i][j] = estadoAux[i-1][j];
					estadoAux[i-1][j] = aux;
				}
			}
		}
		
		return estadoAux;
	}
/*
	public boolean isObjective(Peca[][] estado){
		if(estado[0][0].equals(1) && estado[0][1].equals(2) && estado[0][2].equals(3) && estado[1][0].equals(4) && estado[1][1].equals(5) &&
				estado[1][2].equals(6) && estado[2][0].equals(7) && estado[2][1].equals(8) && estado[2][2].equals(9)){
			return true;
		}else{
			return false;
		}
	}
	*/
	public boolean isObjective(Peca[][] estado){
		int cont = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(estado[i][j].getValor() == estadoObjetivo[i][j].getValor()){
					cont++;
				}
			}
		}
		
		if(cont == 9){
			return true;
		}else{
			return false;
		}
	}

	public Peca[][] getEstadoInicial(){
		return estadoInicial;
	}
	
	public Peca[][] getEstadoFinal(){
		return estadoObjetivo;
	}
	
	public void setEstadoAtual(Peca[][] estadoAtual){
		this.estadoAtual = estadoAtual;
	}
	
	public Peca[][] getEstadoAtual(){
		Peca[][] aux = new Peca[3][3];
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				aux[i][j] = estadoAtual[i][j];
			}
		}
		return aux;
	}
	
	public String imprimirEstado(Peca[][] aux){
		String saida = "";
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				saida += aux[i][j].getValor() + " ";
			}
			saida +="\n";
		}
		
		return saida;
	}
}
