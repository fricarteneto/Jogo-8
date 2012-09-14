package buscas;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import entidades.Peca;
import entidades.Tabuleiro;

public class BuscaEmLargura {

	private Queue<Peca[][]> filaAtual;
	private Queue<Peca[][]> filaVisitados;
	private Tabuleiro tab;
	
	int numNos = 0;
	int numNosVisitados = 0;
	int numNivel = 0;
	
	public BuscaEmLargura() {
		// TODO Auto-generated constructor stub
		filaAtual = new LinkedList<Peca[][]>();
		filaVisitados = new LinkedList<Peca[][]>();
		
		tab = new Tabuleiro();
		
		inicio(tab.getEstadoInicial());
	}

	private void inicio(Peca[][] estadoInicial) {
		// TODO Auto-generated method stub
		filaAtual.add(estadoInicial);
		filaVisitados.add(estadoInicial);
	}
	
	private void push(Peca[][] estado){
		filaAtual.add(estado);
		filaVisitados.add(estado);
		
		System.out.println("=======================================");
		System.out.println("Estados Expandidos");
		String resultado = tab.imprimirEstado(estado);
		System.out.println(resultado);
	}
	
	public String iniciarBusca(){
		
		/*Peca[][] estadoAtual = filaAtual.peek();
		tab.setEstadoAtual(estadoAtual);
		System.out.println("=======================================");
		System.out.println("Estados Atual");
		System.out.println(tab.imprimirEstado(estadoAtual));
		
		String resultado = "";
		expandirEspacos();
		 */
		String resultado = "";
		for(;;){		
			Peca[][] estadoAtual = null;
			if(!filaAtual.isEmpty()){
				estadoAtual = filaAtual.peek();
				tab.setEstadoAtual(estadoAtual);
				
				System.out.println("=======================================");
				System.out.println("Estados Atual");
				System.out.println(tab.imprimirEstado(estadoAtual));
				numNosVisitados++;
			}
			
			if(tab.isObjective(estadoAtual)){
				System.out.println("Estado Final");
				resultado = tab.imprimirEstado(estadoAtual);
				break;
			}else{
				expandirEspacos();
				filaAtual.poll();
			}
			System.out.println(numNos);
		}
		return /*"Número de Niveis " + numNivel +*/ ", número de nós Visitados" + numNosVisitados + "\n" + resultado;
	}
	
	private void expandirEspacos(){
		
		int linhaPecaB = 0;
		int colunaPecaB = 0;
		
		Peca[][] estadoAtual = filaAtual.peek();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(estadoAtual[i][j].getValor() == 0){
					linhaPecaB = i;
					colunaPecaB = j;
				}
			}
		}
		
		Peca[][] aux = null;
		
		if(linhaPecaB>0){
			aux = tab.movimentarPecas('c');
			if(!contem(aux)){
				push(aux);
				numNos++;	
			}
		}
		
		if(linhaPecaB<3){
			aux = tab.movimentarPecas('b');
			if(!contem(aux)){
				push(aux);
				numNos++;	
			}
		}
		
		if(colunaPecaB>0){
			aux = tab.movimentarPecas('e');
			if(!contem(aux)){
				push(aux);
				numNos++;	
			}
		}
		if(colunaPecaB<3){
			aux = tab.movimentarPecas('d');
			if(!contem(aux)){
				push(aux);
				numNos++;	
			}
		}
		//numNivel ++;
	}
	
	private boolean contem(Peca[][] alvo){

		boolean flag = false;
		
		for(Peca[][] iterator: filaVisitados){
			int cont = 0;
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(iterator[i][j].getValor() == alvo[i][j].getValor()){
						cont++;
					}
				}
			}
			
			if(cont == 9){
				flag = true;
				break;
			}

		}
		return flag;
	}
	
}
