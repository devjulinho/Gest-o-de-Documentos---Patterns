package br.ifba.edu.inf011.command.macro;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.SalvarDocumentoCommand;
import br.ifba.edu.inf011.model.documentos.Documento;

public class AlterarEAssinarMacro extends MacroCommand {
	
	private Documento documentoInicial;
	private String novoConteudo;
	private DocumentOperatorFactory factory;
	
	public AlterarEAssinarMacro(Documento documento, String novoConteudo, DocumentOperatorFactory factory) {
		super();  // Chama o construtor do MacroCommand
		this.documentoInicial = documento;
		this.novoConteudo = novoConteudo;
		this.factory = factory;
	}
	
	@Override
	public Documento execute() {
		// Salva o estado INICIAL (cópia profunda do documento antes da macro)
		this.salvarEstadoInicial(documentoInicial);
		
		Documento documentoAtual = documentoInicial;
		
		SalvarDocumentoCommand salvarCmd = new SalvarDocumentoCommand(documentoAtual, novoConteudo);
		documentoAtual = salvarCmd.execute();
		
		AssinarDocumentoCommand assinarCmd = new AssinarDocumentoCommand(documentoAtual, factory);
		documentoAtual = assinarCmd.execute();
		
		// Salva o estado FINAL (depois da macro)
		this.salvarEstadoFinal(documentoAtual);
		
		return documentoAtual;
	}
}
