package br.ifba.edu.inf011.command;

import java.util.List;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GestorDocumento;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;
import br.ifba.edu.inf011.strategy.AutenticacaoFactory;
import br.ifba.edu.inf011.strategy.AutenticacaoStrategy;

public class TornarUrgenteCommand implements Command{
	private Documento documento;
	GestorDocumento gestor = new GestorDocumento();
	
	public TornarUrgenteCommand(Documento documento) {
		this.documento = documento;
	}

	public Documento execute() {
        if (documento == null)
        	return null;
        Documento urgente = gestor.tornarUrgente(documento);
        this.atualizarRepositorio(doc, urgente);
        this.atual = urgente;
        return urgente;  
	}
	
//	public void undo() {
//
//	}
}
