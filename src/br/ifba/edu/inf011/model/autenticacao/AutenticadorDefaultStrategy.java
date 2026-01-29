package br.ifba.edu.inf011.model.autenticacao;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorDefaultStrategy implements AutenticacaoStrategy {

	public void gerarNumero(Documento documento) {
		String numero;
		
		numero = "DOC-" + System.currentTimeMillis();
		
		documento.setNumero(numero);
		
	}
	
}
