package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;
import br.ifba.edu.inf011.strategy.AutenticacaoFactory;
import br.ifba.edu.inf011.strategy.AutenticacaoStrategy;

public class CriarDocumentoCommand implements Command{
	private DocumentOperatorFactory factory;
	private Privacidade privacidade;
    private AutenticacaoFactory gestorAutenticacao = new AutenticacaoFactory();
    private AutenticacaoStrategy autenticadorStrategy;
    private Autenticador autenticador;
    private GerenciadorDocumentoModel repositorio;
	
	public CriarDocumentoCommand(int tipoAutenticadorIndex, Privacidade privacidade, DocumentOperatorFactory factory) {
		this.privacidade = privacidade;
		autenticadorStrategy = gestorAutenticacao.getStrategy(tipoAutenticadorIndex);
		this.autenticador = new Autenticador(autenticadorStrategy);
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
	        
		} catch (FWDocumentException exception){
			System.out.println("exception");
		}
		
		return documento;
	}
	
//	public void undo() {
//		this.repositorio.remove(documento);
//	}
}
