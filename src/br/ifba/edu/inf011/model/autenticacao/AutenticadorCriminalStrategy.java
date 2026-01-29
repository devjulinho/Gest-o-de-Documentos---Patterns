package br.ifba.edu.inf011.model.autenticacao;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorCriminalStrategy implements AutenticacaoStrategy {
	
	public void gerarNumero(Documento documento) {
		String numero;
		
		numero = "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();
		
		documento.setNumero(numero);
		
	}

}
