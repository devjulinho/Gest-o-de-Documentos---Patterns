package br.ifba.edu.inf011.command.macro;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.SalvarDocumentoCommand;
import br.ifba.edu.inf011.model.documentos.Documento;

/**
 * Macro que executa a sequência: Salvar → Assinar
 * Esta é uma "Ação Rápida" que permite editar e assinar um documento em um único bloco.
 */
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
		
		// Passo 1: Salvar o novo conteúdo
		SalvarDocumentoCommand salvarCmd = new SalvarDocumentoCommand(documentoAtual, novoConteudo);
		documentoAtual = salvarCmd.execute();
		
		// Passo 2: Assinar o documento ATUALIZADO (usa o documento resultante do passo 1)
		AssinarDocumentoCommand assinarCmd = new AssinarDocumentoCommand(documentoAtual, factory);
		documentoAtual = assinarCmd.execute();
		
		return documentoAtual;
	}
}
