package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;

public class CriarDocumentoCommand implements Command{
	private DocumentOperatorFactory factory;
	private Privacidade privacidade;
    private Autenticador autenticador;
    private GerenciadorDocumentoModel repositorio;
    private Documento documentoNovo;
	
	public CriarDocumentoCommand(int tipoAutenticadorIndex, Privacidade privacidade, DocumentOperatorFactory factory) {
		this.privacidade = privacidade;
		this.autenticador = new Autenticador(tipoAutenticadorIndex);
		this.factory = factory;
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}

	public Documento execute() {
		Documento documento = null;
		try {
			documento = factory.getDocumento();
			Operador operador = factory.getOperador();
			
	        operador.inicializar("jdc", "João das Couves");
	        documento.inicializar(operador, privacidade);
	        
	        this.autenticador.autenticar(documento);
	        
	        repositorio.addDocumento(documento);
	        
	        this.documentoNovo = documento;
	        
		} catch (FWDocumentException exception){
			System.out.println("exception");
		}
		
		return documento;
	}
	
	public Documento getDocumentoNovo() {
		return documentoNovo;
	}
	
//	public void undo() {
//		this.repositorio.remove(documento);
//	}
}
