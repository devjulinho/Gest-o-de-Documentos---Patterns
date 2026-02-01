package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.model.documentos.Documento;

public class MacroCommand implements Command{

	private List<Command> commands = new ArrayList<Command>();
	
	public void adicionarCommand(Command command) {
		commands.add(command);
	}
	
	public void removerCommand(Command command) {
		commands.remove(command);
	}
	
	public Documento execute() {
		for (Command command : commands) {
			command.execute();
		}
		
		Command ultimoCommand = commands.get(commands.size() - 1);
		
		return ultimoCommand.getDocumentoNovo();
	}
	
	public Documento getDocumentoNovo() {
		Command ultimoCommand = commands.get(commands.size() - 1);

		return ultimoCommand.getDocumentoNovo();
	}
}