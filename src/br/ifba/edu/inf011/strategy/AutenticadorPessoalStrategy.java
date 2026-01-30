package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class AutenticadorPessoalStrategy implements AutenticacaoStrategy {

	public void gerarNumero(Documento documento) {
		String numero;
		
		numero = "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
		
		documento.setNumero(numero);
		
	}
	
}
