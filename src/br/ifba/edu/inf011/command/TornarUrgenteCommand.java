package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.GestorDocumento;
import br.ifba.edu.inf011.model.documentos.Documento;

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
	
	public Documento redo() {
		repositorio.atualizarRepositorio(documentoAntigo, documentoNovo);
		repositorio.setDocumentoAtual(documentoNovo);
		return documentoNovo;
	}
	
	public Documento undo() {
		repositorio.atualizarRepositorio(documentoNovo, documentoAntigo);
		repositorio.setDocumentoAtual(documentoAntigo);
		return documentoAntigo;
	}
	
//	public void undo() {
//
//	}
}
