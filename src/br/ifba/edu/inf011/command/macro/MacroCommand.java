package br.ifba.edu.inf011.command.macro;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.model.documentos.Documento;

public class MacroCommand implements Command {
	private List<Command> commands = new ArrayList<Command>();
	private Documento documentoFinal;
	
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
			documentoAtual = command.execute();
		}

		this.documentoFinal = documentoAtual;
		return documentoAtual;
	}
	
	@Override
	public Documento getDocumentoNovo() {
		return this.documentoFinal;
	}

	@Override
	public Documento undo() {
			return null;
	}
	
	@Override
	public Documento redo() {
			return null;
	}
}