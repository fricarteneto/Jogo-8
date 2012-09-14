package buscas;

import java.util.ArrayList;

import entidades.Estado;
import entidades.Peca;
import entidades.Tabuleiro;

public class BuscaAEstrela {

	private ArrayList<Estado> estadosVisitados;
	private ArrayList<Estado> fronteira;
	private Tabuleiro tab;
	private int numNosVisitados;
	private int numNos;
	
	public BuscaAEstrela() {
		// TODO Auto-generated constructor stub
		estadosVisitados = new ArrayList<Estado>();
		fronteira = new ArrayList<Estado>();
		
		tab = new Tabuleiro();
		
		Estado estadoInicial = new Estado();
		estadoInicial.setState(tab.getEstadoInicial());
		estadoInicial.setStateFinal(tab.getEstadoFinal());
		estadoInicial.setCustoEstado(0);
		estadoInicial.setFuncaoHeuristica();
		estadoInicial.setFuncaoAvaliacao();
		estadosVisitados.add(estadoInicial);
		fronteira.add(estadoInicial);
		
	}
	
	public String iniciarBusca(){
		
		String resultado = "";
		Estado atual = null;
		
		for(;;){

			if(!fronteira.isEmpty()){
				atual = fronteira.get(0);
				tab.setEstadoAtual(atual.getState());

				System.out.println("=======================================");
				System.out.println("Estados Atual");
				System.out.println(tab.imprimirEstado(atual.getState()));
				numNosVisitados++;

			}
			if(tab.isObjective(atual.getState())){
				System.out.println("Estado Final");
				resultado = tab.imprimirEstado(atual.getState());
				break;
			}else{
				expandirEspacos();
				fronteira.remove(0);
				ordenaFronteira();
			}
		}
		
		return /*"Número de Niveis " + numNivel +*/ ", número de nós Visitados" + numNosVisitados + "\n" + resultado;
	}

	private void ordenaFronteira() {
		// TODO Auto-generated method stub
		Object[] lista = fronteira.toArray();

		for(int i = lista.length - 1; i >= 1; i--){
			for(int j = 0; j < i; j++){
				if(compare(((Estado) lista[j]).getFuncaoAvaliacao(), ((Estado) lista[j+1]).getFuncaoAvaliacao()) > 0){
					Estado aux = (Estado) lista[j];
					lista[j] = lista[j+1];
					lista[j+1] = aux;
				}
			}
		}
		
		fronteira.clear();
		
		for(int i = 0; i < lista.length; i++){
			fronteira.add((Estado) lista[i]);
		}
		
	}

	private int compare(int funcAval, int funcAval2) {
		// TODO Auto-generated method stub
		if(funcAval > funcAval2){
			return 1;
		}else if(funcAval < funcAval2){
			return -1;
		}else{
			return 0;
		}
	}

	private void expandirEspacos() {
		// TODO Auto-generated method stub
		
		int linhaPecaB = 0;
		int colunaPecaB = 0;
		
		Peca[][] estadoAtual = fronteira.get(0).getState();
		int custoPai = fronteira.get(0).getCustoEstado();
		Peca[][] estadoObjetivo = fronteira.get(0).getStateFinal();
		
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
				push(aux, custoPai, estadoObjetivo);
				numNos++;	
			}
		}
		
		if(linhaPecaB<3){
			aux = tab.movimentarPecas('b');
			if(!contem(aux)){
				push(aux, custoPai, estadoObjetivo);
				numNos++;	
			}
		}
		
		if(colunaPecaB>0){
			aux = tab.movimentarPecas('e');
			if(!contem(aux)){
				push(aux, custoPai, estadoObjetivo);
				numNos++;	
			}
		}
		if(colunaPecaB<3){
			aux = tab.movimentarPecas('d');
			if(!contem(aux)){
				push(aux, custoPai, estadoObjetivo);
				numNos++;	
			}
		}
		
	}

	private void push(Peca[][] aux, int custoPai, Peca[][] estadoObjetivo) {
		// TODO Auto-generated method stub
		Estado novo = new Estado();
		novo.setState(aux);
		novo.setStateFinal(estadoObjetivo);
		novo.setCustoEstado(custoPai + 1);
		novo.setFuncaoHeuristica();
		novo.setFuncaoAvaliacao();
		
		fronteira.add(novo);
		estadosVisitados.add(novo);
	}

	private boolean contem(Peca[][] alvo){

		boolean flag = false;
		
		for(Estado iterator : estadosVisitados){
			int cont = 0;
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					Peca[][] aux = iterator.getState();
					if(aux[i][j].getValor() == alvo[i][j].getValor()){
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
