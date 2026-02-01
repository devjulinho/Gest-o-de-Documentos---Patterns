package br.ifba.edu.inf011.model.autenticacao;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorCriminalHandler extends AutenticadorHandler {

	public AutenticadorCriminalHandler () {
		super();
	}

	@Override
	protected boolean podeProcessar(int tipo) {
		return tipo == 0;
	}

	public void gerarNumero(Documento documento) {
		String numero = "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();		
		documento.setNumero(numero);		
	}

}
