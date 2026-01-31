package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.documentos.Documento;

public interface Command {
	
	public Documento execute();
//	public void unexecute();
	
}
