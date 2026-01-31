package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.command.CriarDocumentoCommand;
import br.ifba.edu.inf011.command.ProtegerDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteCommand;
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
            this.controller.salvarDocumento(this.atual, this.areaEdicao.getConteudo());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
        }
    }	
	
	protected void protegerDocumento() {
		this.executarComando(new ProtegerDocumentoCommand(this.atual));
		this.refreshUI();
//		try {
//			this.executarComando(new ProtegerDocumentoCommand(this.atual));
////			this.controller.protegerDocumento(this.atual);
//			this.refreshUI();
//		} catch (FWDocumentException e) {
//			JOptionPane.showMessageDialog(this, "Erro ao proteger: " + e.getMessage());
//		}
	}

	protected void assinarDocumento() {
		this.executarComando(new AssinarDocumentoCommand(this.atual));
		this.refreshUI();
//		try {
//			this.controller.assinarDocumento(this.atual);
//			this.refreshUI();
//		} catch (FWDocumentException e) {
//			JOptionPane.showMessageDialog(this, "Erro ao assinar: " + e.getMessage());
//		}		
	}
	
	protected void tornarUrgente() {
		this.executarComando(new TornarUrgenteCommand(this.atual));
		this.refreshUI();
//		try {
//			this.controller.tornarUrgente(this.atual);
//			this.refreshUI();
//		} catch (FWDocumentException e) {
//			JOptionPane.showMessageDialog(this, "Erro ao tornar urgente: " + e.getMessage());
//		}		
	}	

	private void criarDocumento(Privacidade privacidade) {
		int tipoIndex = this.barraSuperior.getTipoSelecionadoIndice();
		this.atual = this.executarComando(new CriarDocumentoCommand(tipoIndex, privacidade, factory));
		this.barraDocs.addDoc("[" + atual.getNumero() + "]");
		this.refreshUI();
//        try {
//            int tipoIndex = this.barraSuperior.getTipoSelecionadoIndice();
//            this.atual = this.controller.criarDocumento(tipoIndex, privacidade);
//            this.barraDocs.addDoc("[" + atual.getNumero() + "]");
//            this.refreshUI();
//        } catch (FWDocumentException e) {
//            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
//        }
    }	
	
	private Documento executarComando(Command command) {
		Documento doc;
		doc = command.execute();
		return doc;
	}
	

}
