package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
	
	private List<Command> historico = new ArrayList<Command>();
	private int virtualSize;
	private LogCommand logger = new LogCommand();
	
	public void salvarHistorico(Command command) {
		if(virtualSize != historico.size() && virtualSize > 0) {
			historico = historico.subList(0, virtualSize - 1);
		}
		historico.add(command);
		virtualSize = historico.size();
		logger.log(command.toString());
	}
	
	public Command desfazer() {
		if (virtualSize == -1)
			return null;
		
		if (virtualSize < 0) {
			Command command = historico.get(0);
			logger.log("{DESFAZER} -" + command.toString());
			return command;
		}
		virtualSize--;
		Command command = historico.get(virtualSize);
		logger.log("{DESFAZER} -" + command.toString());
		return command;
	}
	
	public Command refazer() {
		if (virtualSize == -1)
			return null;
		
		if (virtualSize > historico.size() - 1) {
			Command command = historico.get(historico.size() - 1);
			logger.log("{REFAZER} -" + command.toString());
			return command;
		}
		Command command = historico.get(virtualSize);
		logger.log("{REFAZER} -" + command.toString());
		virtualSize++;
		return command;
	}
	
	public void consolidar() {
		historico.clear();
		virtualSize = -1;
	}
}
