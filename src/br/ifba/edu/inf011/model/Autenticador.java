package br.ifba.edu.inf011.model;

import br.ifba.edu.inf011.model.autenticacao.AutenticadorHandler;
import br.ifba.edu.inf011.model.documentos.Documento;

public class Autenticador {
	private int tipo;
	
	public Autenticador(int tipo) {
		this.tipo = tipo;
	}
	
	public void autenticar(Documento documento) {
		AutenticadorHandler.head.autenticar(documento, this.tipo);
	}
}
