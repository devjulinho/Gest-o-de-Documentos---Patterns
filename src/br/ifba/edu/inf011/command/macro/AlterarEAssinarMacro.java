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
		this.documentoInicial = documento;
		this.novoConteudo = novoConteudo;
		this.factory = factory;
	}
	
	@Override
	public Documento execute() {
		Documento documentoAtual = documentoInicial;
		
		SalvarDocumentoCommand salvarCmd = new SalvarDocumentoCommand(documentoAtual, novoConteudo);
		documentoAtual = salvarCmd.execute();
		
		AssinarDocumentoCommand assinarCmd = new AssinarDocumentoCommand(documentoAtual, factory);
		documentoAtual = assinarCmd.execute();
		
		return documentoAtual;
	}
}
