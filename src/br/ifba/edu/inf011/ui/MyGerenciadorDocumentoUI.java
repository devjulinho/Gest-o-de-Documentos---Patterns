package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.Command;
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
	
	 public MyGerenciadorDocumentoUI(DocumentOperatorFactory factory) {
		super(factory);
		this.factory = factory;
	}

	protected JPanelOperacoes montarMenuOperacoes() {
		JPanelOperacoes comandos = new JPanelOperacoes();
		comandos.addOperacao("➕ Criar Publico", e -> this.criarDocumentoPublico());
		comandos.addOperacao("➕ Criar Privado", e -> this.criarDocumentoPrivado());
		comandos.addOperacao("💾 Salvar", e-> this.salvarConteudo());
		comandos.addOperacao("🔑 Proteger", e->this.protegerDocumento());
		comandos.addOperacao("✍️ Assinar", e->this.assinarDocumento());
		comandos.addOperacao("⏰ Urgente", e->this.tornarUrgente());
		// MACROS - Ações Rápidas
		comandos.addOperacao("📝✍️ Alterar e Assinar", e->this.alterarEAssinar());
		comandos.addOperacao("🔥✍️ Priorizar", e->this.priorizar());
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
	
	// ========== MACROS - Ações Rápidas ==========
	
	/**
	 * Macro: Alterar e Assinar
	 * Permite editar o conteúdo do documento e assinar em uma única operação.
	 */
	protected void alterarEAssinar() {
		if (this.atual == null) {
			JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
			return;
		}
		
		try {
			// Pega o conteúdo atual da área de edição
			String novoConteudo = this.areaEdicao.getConteudo();
			
			// Executa a macro: Salvar → Assinar
			this.atual = this.executarComando(new AlterarEAssinarMacro(this.atual, novoConteudo, factory));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Alterar e Assinar: " + e.getMessage());
		}
	}
	
	/**
	 * Macro: Priorizar
	 * Torna o documento urgente e assina em uma única operação.
	 */
	protected void priorizar() {
		if (this.atual == null) {
			JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
			return;
		}
		
		try {
			// Executa a macro: Tornar Urgente → Assinar
			this.atual = this.executarComando(new PriorizarMacro(this.atual, factory));
			this.refreshUI();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao Priorizar: " + e.getMessage());
		}
	}

	private void criarDocumento(Privacidade privacidade) {
		int tipoIndex = this.barraSuperior.getTipoSelecionadoIndice();
		this.atual = this.executarComando(new CriarDocumentoCommand(tipoIndex, privacidade, factory));
		this.barraDocs.addDoc("[" + atual.getNumero() + "]");
		this.refreshUI();
    }	
	
	private Documento executarComando(Command command) {
		Documento doc;
		doc = command.execute();
		return doc;
	}
	

}
