package br.ifba.edu.inf011.model.autenticacao;

import br.ifba.edu.inf011.model.documentos.Documento;

public interface AutenticacaoStrategy {
	
	public void gerarNumero(Documento documento);

}
