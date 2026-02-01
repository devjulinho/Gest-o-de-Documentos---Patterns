package br.ifba.edu.inf011.command;

import java.util.List;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.GestorDocumento;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;
import br.ifba.edu.inf011.strategy.AutenticacaoFactory;
import br.ifba.edu.inf011.strategy.AutenticacaoStrategy;

public class TornarUrgenteCommand implements Command{
	private Documento documentoAntigo;
	private Documento documentoNovo;
	private GestorDocumento gestor = new GestorDocumento();
	private GerenciadorDocumentoModel repositorio;
	
	public TornarUrgenteCommand(Documento documento) {
		this.documentoAntigo = documento;
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}

	public Documento execute() {
        if (documentoAntigo == null)
        	return null;
        Documento urgente = gestor.tornarUrgente(documentoAntigo);
        repositorio.atualizarRepositorio(documentoAntigo, urgente);
        repositorio.setDocumentoAtual(urgente);
        documentoNovo = urgente;
        return urgente;  
	}
	
	public Documento getDocumentoNovo() {
		return documentoNovo;
	}
	
//	public void undo() {
//
//	}
}
