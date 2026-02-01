package br.ifba.edu.inf011.model;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.*;
import br.ifba.edu.inf011.model.autenticacao.InicializadorChain;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class GerenciadorDocumentoModel {

	private static GerenciadorDocumentoModel instance;
	private List<Documento> repositorio;
    private DocumentOperatorFactory factory;
    private Documento atual;
    private CommandManager commandManager;
    
    public GerenciadorDocumentoModel(DocumentOperatorFactory factory) {
        this.repositorio = new ArrayList<>();
        this.factory = factory;
        this.atual = null;
        this.commandManager = new CommandManager();
        instance = this;

        InicializadorChain.inicializar();
    }
    
    public static GerenciadorDocumentoModel getInstance() {
        return instance;
    }

    public Documento criarDocumento(int tipoAutenticadorIndex, Privacidade privacidade) throws FWDocumentException {
        Command command = new CriarDocumentoCommand(tipoAutenticadorIndex, privacidade, factory);
        Documento documento = command.execute();
        commandManager.salvarHistorico(command);
        this.atual = documento;
        return documento;
    }

    public void salvarDocumento(Documento doc, String conteudo) throws Exception {
        Command command = new SalvarDocumentoCommand(doc, conteudo);
        command.execute();
        commandManager.salvarHistorico(command);
        this.atual = doc;
    }

    public List<Documento> getRepositorio() {
        return repositorio;
    }
    
    public void addDocumento(Documento documento) {
        this.repositorio.add(documento);
    }
    
    public Documento assinarDocumento(Documento doc) throws FWDocumentException {
        Command command = new AssinarDocumentoCommand(doc, factory);
        Documento assinado = command.execute();
        commandManager.salvarHistorico(command);
        return assinado;
    }    
    
    public Documento protegerDocumento(Documento doc) throws FWDocumentException {
        Command command = new ProtegerDocumentoCommand(doc);
        Documento protegido = command.execute();
        commandManager.salvarHistorico(command);
        return protegido;
    }    
    
    
    public Documento tornarUrgente(Documento doc) throws FWDocumentException {
        Command command = new TornarUrgenteCommand(doc);
        Documento urgente = command.execute();
        commandManager.salvarHistorico(command);
        return urgente;
    }      
    
    public void atualizarRepositorio(Documento antigo, Documento novo) {
        int index = repositorio.indexOf(antigo);
        if (index != -1) {
            repositorio.set(index, novo);
        }
    } 
    
	public Documento getDocumentoAtual() {
		return this.atual;
	}
	
	public void setDocumentoAtual(Documento doc) {
		this.atual = doc;
	}        
    
    
}
