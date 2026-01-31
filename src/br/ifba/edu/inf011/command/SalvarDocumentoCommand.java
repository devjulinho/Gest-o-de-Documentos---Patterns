package br.ifba.edu.inf011.command;

import java.util.List;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;
import br.ifba.edu.inf011.strategy.AutenticacaoFactory;
import br.ifba.edu.inf011.strategy.AutenticacaoStrategy;

public class SalvarDocumentoCommand implements Command{
	
	public SalvarDocumentoCommand(Documento documento, String conteudo) {

	}

	public Documento execute() {

	}
	
//	public void undo() {
//
//	}
}
