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
		if (documento != null) {
			try {
				conteudoAntigo = documento.getConteudo();
				documento.setConteudo(conteudoNovo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		repositorio.setDocumentoAtual(documento);
		return documento;
	}
	
	public Documento redo() {
		documento.setConteudo(conteudoNovo);
		repositorio.setDocumentoAtual(documento);
		return this.documento;
	}
	
	public Documento undo() {
		documento.setConteudo(conteudoAntigo);
		repositorio.setDocumentoAtual(documento);
		return this.documento;
	}
	
//	public void undo() {
//
//	}
}
