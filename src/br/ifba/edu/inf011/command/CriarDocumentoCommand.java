package br.ifba.edu.inf011.command;

import java.util.List;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;
import br.ifba.edu.inf011.strategy.AutenticacaoFactory;
import br.ifba.edu.inf011.strategy.AutenticacaoStrategy;

public class CriarDocumentoCommand implements Command{
	private DocumentOperatorFactory factory;
	private Documento documento;
	private Operador operador;
	private int tipoAutenticadorIndex;
	private Privacidade privacidade;
    private AutenticacaoFactory gestorAutenticacao = new AutenticacaoFactory();
    private AutenticacaoStrategy autenticadorStrategy;
    private Autenticador autenticador;
	private List<Documento> repositorio;
	
	public CriarDocumentoCommand(Documento documento, Operador operador, int tipoAutenticadorIndex, Privacidade privacidade, DocumentOperatorFactory factory) {
		this.factory = factory;
		this.documento = documento;
		this.operador = operador;
		this.tipoAutenticadorIndex = tipoAutenticadorIndex;
		this.privacidade = privacidade;
	}

	public void execute() {
		try {
			documento = factory.getDocumento();
			operador = factory.getOperador();
			
	        operador.inicializar("jdc", "João das Couves");
	        documento.inicializar(operador, privacidade);
	        autenticadorStrategy = gestorAutenticacao.getStrategy(tipoAutenticadorIndex);
	        autenticador = new Autenticador(autenticadorStrategy);
	        
	        this.autenticador.autenticar(documento);
	        
	        this.repositorio.add(documento);
			
		} catch (FWDocumentException exception){
			System.out.println("exception");
		}
	}
}
