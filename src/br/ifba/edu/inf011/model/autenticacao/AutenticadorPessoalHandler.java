package br.ifba.edu.inf011.model.autenticacao;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorPessoalHandler extends AutenticadorHandler {
    
	public AutenticadorPessoalHandler() {
		super();
	}

	@Override
	protected boolean podeProcessar(int tipo) {
		return tipo == 1;
	}

	@Override
	public void gerarNumero(Documento documento) {
		String numero = "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
		documento.setNumero(numero);
	}
	
}
