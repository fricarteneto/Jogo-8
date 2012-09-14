import buscas.BuscaAEstrela;
import buscas.BuscaEmLargura;
import buscas.BuscaEmProfundidade;


public class Main {

	public static void main(String args[]){
		/*
		BuscaEmLargura largura = new BuscaEmLargura();
		System.out.println(largura.iniciarBusca());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BuscaEmProfundidade profundidade = new BuscaEmProfundidade();
		System.out.println(profundidade.iniciarBusca());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		BuscaAEstrela aEstrela = new BuscaAEstrela();
		System.out.println(aEstrela.iniciarBusca());
	}
}
