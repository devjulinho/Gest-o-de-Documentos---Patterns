package br.ifba.edu.inf011.command.macro;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteCommand;
import br.ifba.edu.inf011.model.documentos.Documento;

/**
 * Macro que executa a sequência: Tornar Urgente → Assinar
 * Esta é uma "Ação Rápida" que prioriza um documento tornando-o urgente e assinando em um único bloco.
 */
public class PriorizarMacro extends MacroCommand {
	
	private Documento documentoInicial;
	private DocumentOperatorFactory factory;
	
	public PriorizarMacro(Documento documento, DocumentOperatorFactory factory) {
		this.documentoInicial = documento;
		this.factory = factory;
	}
	
	@Override
	public Documento execute() {
		Documento documentoAtual = documentoInicial;
		
		TornarUrgenteCommand tornarUrgenteCmd = new TornarUrgenteCommand(documentoAtual);
		documentoAtual = tornarUrgenteCmd.execute();
		AssinarDocumentoCommand assinarCmd = new AssinarDocumentoCommand(documentoAtual, factory);
		documentoAtual = assinarCmd.execute();
		
		return documentoAtual;
	}
}
