package br.ifba.edu.inf011.model.autenticacao;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class AutenticadorExportacaoHandler extends AutenticadorHandler {

	public AutenticadorExportacaoHandler() {
		super();
	}

	@Override
	protected boolean podeProcessar(int tipo) {
		return tipo == 2;
	}

	@Override
	public void gerarNumero(Documento documento) {
		String numero;
		if (documento.getPrivacidade() == Privacidade.SIGILOSO) {
			numero = "SECURE-" + documento.hashCode();
		} 
		else {
			numero = "PUB-" + documento.hashCode();
		}

		documento.setNumero(numero);
	}
	
}
