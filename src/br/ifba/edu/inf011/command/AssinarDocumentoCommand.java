package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.GestorDocumento;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.operador.Operador;

public class AssinarDocumentoCommand implements Command{ //TALVEZ NEM PRECISE DESSA CLASSE
	private Documento documentoNovo;
	private Documento documentoAntigo;
	private DocumentOperatorFactory factory;
	private GestorDocumento gestor = new GestorDocumento();
	private GerenciadorDocumentoModel repositorio;
	
	public AssinarDocumentoCommand(Documento documento, DocumentOperatorFactory factory) {
		this.documentoAntigo = documento;
		this.repositorio = GerenciadorDocumentoModel.getInstance();
		this.factory = factory;
	}

	public Documento execute() {
        if (documentoAntigo == null) {
        	throw new RuntimeException("Nenhum documento selecionado para assinar.");
        }
        
        Documento assinado = null;
        try {
	        Operador operador = factory.getOperador();
	        operador.inicializar("jdc", "João das Couves");
	        assinado = gestor.assinar(documentoAntigo, operador);
	        repositorio.atualizarRepositorio(documentoAntigo, assinado);
	        repositorio.setDocumentoAtual(assinado);
	        
	        documentoNovo = assinado;
	        
		} catch (FWDocumentException e) {
			throw new RuntimeException("Erro ao assinar o documento: " + e.getMessage(), e);
		}
        return assinado;
	}
	
	public Documento redo() {
		repositorio.atualizarRepositorio(documentoAntigo, documentoNovo);
		repositorio.setDocumentoAtual(documentoNovo);
		return this.documentoNovo;
	}
	
	public Documento undo() {
		repositorio.atualizarRepositorio(documentoNovo, documentoAntigo);
		repositorio.setDocumentoAtual(documentoAntigo);
		return this.documentoAntigo;
	}
}
