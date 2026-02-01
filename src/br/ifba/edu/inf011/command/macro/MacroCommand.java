package br.ifba.edu.inf011.command.macro;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class MacroCommand implements Command {
	private List<Command> commands = new ArrayList<Command>();
	private Documento documentoAntigo;
	private Documento documentoNovo;
	private GerenciadorDocumentoModel repositorio;
	
	public MacroCommand() {
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}
	
	protected void salvarEstadoInicial(Documento documento) {
		this.documentoAntigo = copiarDocumento(documento);
	}
	
	protected void salvarEstadoFinal(Documento documento) {
		this.documentoNovo = documento;
	}
	
	private Documento copiarDocumento(Documento original) {
		try {
			Documento copia = original.getClass().getDeclaredConstructor().newInstance();
			copia.setConteudo(original.getConteudo());
			copia.setNumero(original.getNumero());
			copia.inicializar(original.getProprietario(), original.getPrivacidade());
			return copia;
		} catch (Exception e) {
			return original;
		}
	}
	
	public void adicionarCommand(Command command) {
		commands.add(command);
	}
	
	public void removerCommand(Command command) {
		commands.remove(command);
	}
	
	@Override
	public Documento execute() {
		Documento documentoAtual = null;

		for (Command command : commands) {
			if (documentoAntigo == null) {
				documentoAntigo = documentoAtual;
			}
			
			documentoAtual = command.execute();
		}

		this.documentoNovo = documentoAtual;
		return documentoAtual;
	}
	
	@Override
	public Documento getDocumentoNovo() {
		return this.documentoNovo;
	}

	@Override
	public Documento undo() {
		if (documentoAntigo != null && documentoNovo != null) {
			repositorio.atualizarRepositorio(documentoNovo, documentoAntigo);
			repositorio.setDocumentoAtual(documentoAntigo);
		}
		return documentoAntigo;
	}
	
	@Override
	public Documento redo() {
		if (documentoAntigo != null && documentoNovo != null) {
			repositorio.atualizarRepositorio(documentoAntigo, documentoNovo);
			repositorio.setDocumentoAtual(documentoNovo);
		}
		return documentoNovo;
	}
}