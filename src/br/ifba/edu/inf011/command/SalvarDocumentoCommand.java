package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class SalvarDocumentoCommand implements Command{
	private Documento documento;
	private String conteudoAntigo;
	private String conteudoNovo;
	private GerenciadorDocumentoModel repositorio;
	
	public SalvarDocumentoCommand(Documento documento, String conteudo) {
		this.documento = documento;
		this.conteudoNovo = conteudo;
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}

	public Documento execute() {
		if (documento == null) {
			throw new RuntimeException("Nenhum documento selecionado para salvar.");
		}
		
		try {
			conteudoAntigo = documento.getConteudo();
			documento.setConteudo(conteudoNovo);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar o conteúdo do documento: " + e.getMessage(), e);
		}
		
		repositorio.setDocumentoAtual(documento);
		return documento;
	}
	
	public Documento getDocumentoNovo() {
		return this.documento;
	}
	
//	public void undo() {
//
//	}
}
