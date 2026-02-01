package br.ifba.edu.inf011.command;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogCommand {
	
	private static final String LOG_FILE = "DocumentEditor.txt";
	
	public void log(String acao) {
		try (FileWriter fwriter = new FileWriter(LOG_FILE, true);
			PrintWriter pwriter = new PrintWriter(fwriter);)
		{
			String dataTime = LocalDateTime.now().toString();
			String logEntry = "[ ] -> " + dataTime + ":" + acao.toString();
			pwriter.println(logEntry);	
		}
		catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
		}
	}
}
