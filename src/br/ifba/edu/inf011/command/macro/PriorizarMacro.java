package br.ifba.edu.inf011.command.macro;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteCommand;
import br.ifba.edu.inf011.model.documentos.Documento;

public class PriorizarMacro extends MacroCommand {
	
	private Documento documentoInicial;
	private DocumentOperatorFactory factory;
	
	public PriorizarMacro(Documento documento, DocumentOperatorFactory factory) {
		super();  // Chama o construtor do MacroCommand
		this.documentoInicial = documento;
		this.factory = factory;
	}
	
	@Override
	public Documento execute() {
		// Salva o estado INICIAL (cópia profunda do documento antes da macro)
		this.salvarEstadoInicial(documentoInicial);
		
		Documento documentoAtual = documentoInicial;
		
		TornarUrgenteCommand tornarUrgenteCmd = new TornarUrgenteCommand(documentoAtual);
		documentoAtual = tornarUrgenteCmd.execute();
		AssinarDocumentoCommand assinarCmd = new AssinarDocumentoCommand(documentoAtual, factory);
		documentoAtual = assinarCmd.execute();
		
		// Salva o estado FINAL (depois da macro)
		this.salvarEstadoFinal(documentoAtual);
		
		return documentoAtual;
	}
}
