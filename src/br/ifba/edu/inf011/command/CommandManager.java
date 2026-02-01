package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
	
	private List<Command> historico = new ArrayList<Command>();
	private int virtualSize;
	
	public void salvarHistorico(Command command) {
		if(virtualSize != historico.size() && virtualSize > 0) {
			historico = historico.subList(0, virtualSize - 1);
		}
		historico.add(command);
		virtualSize = historico.size();
	}
	
	public Command desfazer() {
		if (virtualSize == -1)
			return null;
		
		if (virtualSize < 0) {
			return historico.get(0);
		}
		virtualSize--;
		return historico.get(virtualSize);
	}
	
	public Command refazer() {
		if (virtualSize == -1)
			return null;
		
		if (virtualSize > historico.size() - 1) {
			return historico.get(historico.size() - 1);
		}
		Command command = historico.get(virtualSize);
		virtualSize++;
		return command;
	}
	
	public void consolidar() {
		historico.clear();
		virtualSize = -1;
	}
}
