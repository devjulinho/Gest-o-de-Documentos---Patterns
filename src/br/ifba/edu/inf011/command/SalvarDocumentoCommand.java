package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class SalvarDocumentoCommand implements Command{
	private Documento documentoAntigo;
	private Documento documentoNovo;
	private String conteudoAntigo;
	private String conteudoNovo;
	private GerenciadorDocumentoModel repositorio;
	
	public SalvarDocumentoCommand(Documento documento, String conteudo) {
		this.documentoAntigo = documento;
		this.conteudoNovo = conteudo;
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}

	public Documento execute() {
		if (documentoAntigo == null) {
			throw new RuntimeException("Nenhum documento selecionado para salvar.");
		}
		
		try {
			conteudoAntigo = documentoAntigo.getConteudo();
			documentoAntigo.setConteudo(conteudoNovo);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar o conteúdo do documento: " + e.getMessage(), e);
		}
		
		repositorio.setDocumentoAtual(documentoAntigo);
		documentoNovo = documentoAntigo;
		return documentoAntigo;
	}
	
	public Documento getDocumentoNovo() {
		return documentoNovo;
	}
	
	public Documento redo() {
		documentoAntigo.setConteudo(conteudoNovo);
		repositorio.setDocumentoAtual(documentoAntigo);
		return this.documentoAntigo;
	}
	
	public Documento undo() {
		documentoAntigo.setConteudo(conteudoAntigo);
		repositorio.setDocumentoAtual(documentoAntigo);
		return this.documentoAntigo;
	}
	
	public String toString() {
		return "SalvarDocumentoCommand: " + documentoNovo.getNumero();
	}
}
