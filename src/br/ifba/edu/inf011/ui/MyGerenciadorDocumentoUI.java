package br.ifba.edu.inf011.ui;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.command.CommandManager;
import br.ifba.edu.inf011.command.CriarDocumentoCommand;
import br.ifba.edu.inf011.command.ProtegerDocumentoCommand;
import br.ifba.edu.inf011.command.SalvarDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteCommand;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	
	private DocumentOperatorFactory factory;
	private CommandManager manager;
	
	 public MyGerenciadorDocumentoUI(DocumentOperatorFactory factory) {
		super(factory);
		this.factory = factory;
		this.manager = new CommandManager();
	}

	protected JPanelOperacoes montarMenuOperacoes() {
		JPanelOperacoes comandos = new JPanelOperacoes();
		comandos.addOperacao("➕ Criar Publico", e -> this.criarDocumentoPublico());
		comandos.addOperacao("➕ Criar Privado", e -> this.criarDocumentoPrivado());
		comandos.addOperacao("💾 Salvar", e-> this.salvarConteudo());
		comandos.addOperacao("🔑 Proteger", e->this.protegerDocumento());
		comandos.addOperacao("✍️ Assinar", e->this.assinarDocumento());
		comandos.addOperacao("⏰ Urgente", e->this.tornarUrgente());
		comandos.addOperacao("↩️ Desfazer", e->this.undo());
		comandos.addOperacao("↪️ Refazer", e->this.redo());
		return comandos;
	 }
	
	protected void criarDocumentoPublico() {
		this.criarDocumento(Privacidade.PUBLICO);
	}
	
	protected void criarDocumentoPrivado() {
		this.criarDocumento(Privacidade.SIGILOSO);
	}
	
	protected void salvarConteudo() {
        this.executarComando(new SalvarDocumentoCommand(this.atual, this.areaEdicao.getConteudo()));
        this.refreshUI();
    }	
	
	protected void protegerDocumento() {
		this.executarComando(new ProtegerDocumentoCommand(this.atual));
		this.refreshUI();
	}

	protected void assinarDocumento() {
		this.executarComando(new AssinarDocumentoCommand(this.atual, factory));
		this.refreshUI();	
	}
	
	protected void tornarUrgente() {
		this.executarComando(new TornarUrgenteCommand(this.atual));
        this.refreshUI();	
	}	

	private void criarDocumento(Privacidade privacidade) {
		int tipoIndex = this.barraSuperior.getTipoSelecionadoIndice();
		this.atual = this.executarComando(new CriarDocumentoCommand(tipoIndex, privacidade, factory, barraDocs));
		this.refreshUI();
    }	
	
	private Documento executarComando(Command command) {
		Documento doc;
		doc = command.execute();
		this.manager.salvarHistorico(command);
		return doc;
	}
	
	private void undo() {
		Command command = this.manager.desfazer();
		command.undo();
		this.refreshUI();
	}
	
	private void redo() {
		Command command = this.manager.refazer();
		command.redo();
		this.refreshUI();
	}
	

}
