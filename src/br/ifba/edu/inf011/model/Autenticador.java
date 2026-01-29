package br.ifba.edu.inf011.model;

import br.ifba.edu.inf011.model.autenticacao.AutenticacaoStrategy;
import br.ifba.edu.inf011.model.autenticacao.AutenticadorDefaultStrategy;
import br.ifba.edu.inf011.model.documentos.Documento;

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
