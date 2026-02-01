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
		if (virtualSize == -1) {
			logger.log("{TENTATIVA DESFAZER} - Nenhum comando no histórico");
			return null;
		}
		
		if (virtualSize <= 0) {
			logger.log("{TENTATIVA DESFAZER} - Já está no início do histórico");
			return null;
		}
		
		virtualSize--;
		Command command = historico.get(virtualSize);
		logger.log("{DESFAZER} - " + command.toString());
		return command;
	}
	
	public Command refazer() {
		if (virtualSize == -1) {
			logger.log("{TENTATIVA REFAZER} - Nenhum comando no histórico");
			return null;
		}
		
		if (virtualSize > historico.size() - 1) {
			logger.log("{TENTATIVA REFAZER} - Já está no final do histórico");
			return null;
		}
		
		Command command = historico.get(virtualSize);
		logger.log("{REFAZER} - " + command.toString());
		virtualSize++;
		return command;
	}
	
	public void consolidar() {
		historico.clear();
		virtualSize = -1;
		logger.log("{CONSOLIDAR} - Histórico limpo");
	}
}
