package br.ifba.edu.inf011.model.autenticacao;

public class InicializadorChain {
	private static boolean inicializado = false;
	
	public static void inicializar() {
			if (!inicializado) {
					new AutenticadorDefaultHandler();
					new AutenticadorCriminalHandler();
					new AutenticadorPessoalHandler();
					new AutenticadorExportacaoHandler();
					
					inicializado = true;
			}
	}
}