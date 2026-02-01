package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.documentos.Documento;

public interface Command {
	
	public Documento execute();	
	public Documento getDocumentoNovo();

	public Documento undo();
	public Documento redo();
	
}
