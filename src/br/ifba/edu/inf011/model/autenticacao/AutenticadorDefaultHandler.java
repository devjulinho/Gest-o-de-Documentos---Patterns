package br.ifba.edu.inf011.model.autenticacao;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorDefaultHandler extends AutenticadorHandler {

	public AutenticadorDefaultHandler() {
		super();
	}

	@Override
	protected boolean podeProcessar(int tipo) {
		return false;
	}

	@Override
	public void gerarNumero(Documento documento) {
		String numero = "DOC-" + System.currentTimeMillis();
		documento.setNumero(numero);		
	}
	
}
