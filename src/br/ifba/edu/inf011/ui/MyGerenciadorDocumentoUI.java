package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.command.CommandManager;
import br.ifba.edu.inf011.command.CriarDocumentoCommand;
import br.ifba.edu.inf011.command.ProtegerDocumentoCommand;
import br.ifba.edu.inf011.command.SalvarDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteCommand;
import br.ifba.edu.inf011.command.macro.AlterarEAssinarMacro;
import br.ifba.edu.inf011.command.macro.PriorizarMacro;
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
		comandos.addOperacao("📝✍️ Alterar e Assinar", e->this.alterarEAssinar());
		comandos.addOperacao("⏰✍️ Priorizar", e->this.priorizar());
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
		try {
			this.executarComando(new SalvarDocumentoCommand(this.atual, this.areaEdicao.getConteudo()));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
		}
    }	
	
	protected void protegerDocumento() {
		try {
			this.executarComando(new ProtegerDocumentoCommand(this.atual));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Proteger: " + e.getMessage());
		}
	}

	protected void assinarDocumento() {
		try {
			this.executarComando(new AssinarDocumentoCommand(this.atual, factory));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Assinar: " + e.getMessage());
		}
	}
	
	protected void tornarUrgente() {
		try {
			this.executarComando(new TornarUrgenteCommand(this.atual));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Tornar Urgente: " + e.getMessage());
		}
	}	
	
	protected void alterarEAssinar() {
		if (this.atual == null) {
			JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
			return;
		}
		
		try {
			String novoConteudo = this.areaEdicao.getConteudo();
			
			this.atual = this.executarComando(new AlterarEAssinarMacro(this.atual, novoConteudo, factory));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Alterar e Assinar: " + e.getMessage());
		}
	}

	protected void priorizar() {
		if (this.atual == null) {
			JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
			return;
		}
		
		try {
			this.atual = this.executarComando(new PriorizarMacro(this.atual, factory));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Priorizar: " + e.getMessage());
		}
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
