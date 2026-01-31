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

public class AssinarDocumentoCommand implements Command{ //TALVEZ NEM PRECISE DESSA CLASSE
	private Documento documento;
	private DocumentOperatorFactory factory;
	private GestorDocumento gestor = new GestorDocumento();
	
	public AssinarDocumentoCommand(Documento documento) {
		this.documento = documento;
	}

	public Documento execute() {
        if (documento == null)
        		return null;
        Documento assinado = null;
        try {
	        Operador operador = factory.getOperador();
	        operador.inicializar("jdc", "João das Couves");
	        assinado = gestor.assinar(documento, operador);
		//  this.atualizarRepositorio(documento, assinado);
		// 	this.atual = assinado;
			} catch (FWDocumentException e) {
				e.printStackTrace();
			}
        return assinado;
	}
	
//	public void undo() {
//
//	}
}
