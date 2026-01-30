package br.ifba.edu.inf011.model;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.AutenticacaoStrategy;
import br.ifba.edu.inf011.strategy.AutenticadorDefaultStrategy;

public class Autenticador {
	private AutenticacaoStrategy strategy;
	
	public Autenticador() {
		this.strategy = new AutenticadorDefaultStrategy();
	}
	
	public Autenticador(AutenticacaoStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void autenticar(Documento documento) {
		strategy.gerarNumero(documento);
	}
}
