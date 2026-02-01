package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.GestorDocumento;
import br.ifba.edu.inf011.model.documentos.Documento;

public class ProtegerDocumentoCommand implements Command{
	private Documento documentoAntigo;
	private Documento documentoNovo;
	private GestorDocumento gestor = new GestorDocumento();
	private GerenciadorDocumentoModel repositorio;
	
	public ProtegerDocumentoCommand(Documento documento) {
		this.documentoAntigo = documento;
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}

	public Documento execute() {
        if (documentoAntigo == null)
        	return null;
        Documento protegido = gestor.proteger(documentoAntigo);
        repositorio.atualizarRepositorio(documentoAntigo, protegido);
        repositorio.setDocumentoAtual(protegido);
        documentoNovo = protegido;
        return protegido;  
	}
	
	public Documento getDocumentoNovo() {
		return documentoNovo;
	}
	
	
//	public void undo() {
//
//	}
}
