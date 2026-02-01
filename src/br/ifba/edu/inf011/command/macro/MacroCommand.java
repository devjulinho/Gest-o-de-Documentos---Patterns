package br.ifba.edu.inf011.command.macro;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class MacroCommand implements Command {
	private List<Command> commands = new ArrayList<Command>();
	private Documento documentoAntigo;  // Estado ANTES da macro (cópia)
	private Documento documentoNovo;    // Estado DEPOIS da macro
	private GerenciadorDocumentoModel repositorio;
	
	public MacroCommand() {
		this.repositorio = GerenciadorDocumentoModel.getInstance();
	}
	
	/**
	 * Salva o estado INICIAL do documento (antes da macro).
	 * IMPORTANTE: Cria uma cópia profunda para evitar modificações no original.
	 */
	protected void salvarEstadoInicial(Documento documento) {
		this.documentoAntigo = copiarDocumento(documento);
	}
	
	/**
	 * Salva o estado FINAL do documento (depois da macro).
	 */
	protected void salvarEstadoFinal(Documento documento) {
		this.documentoNovo = documento;
	}
	
	/**
	 * Cria uma cópia profunda do documento para preservar o estado original.
	 * Copia o conteúdo e preserva os metadados.
	 */
	private Documento copiarDocumento(Documento original) {
		try {
			// Cria uma nova instância do mesmo tipo de documento
			Documento copia = original.getClass().getDeclaredConstructor().newInstance();
			copia.setConteudo(original.getConteudo());
			copia.setNumero(original.getNumero());
			// Inicializa com o mesmo proprietário e privacidade
			copia.inicializar(original.getProprietario(), original.getPrivacidade());
			return copia;
		} catch (Exception e) {
			// Se falhar, retorna o original (fallback)
			return original;
		}
	}
	
	public void adicionarCommand(Command command) {
		commands.add(command);
	}
	
	public void removerCommand(Command command) {
		commands.remove(command);
	}
	
	@Override
	public Documento execute() {
		Documento documentoAtual = null;

		for (Command command : commands) {
			// Captura o documento ANTES da primeira execução
			if (documentoAntigo == null) {
				documentoAntigo = documentoAtual;
			}
			
			documentoAtual = command.execute();
		}

		// Guarda o documento FINAL (depois de todos os comandos)
		this.documentoNovo = documentoAtual;
		return documentoAtual;
	}
	
	@Override
	public Documento getDocumentoNovo() {
		return this.documentoNovo;
	}

	@Override
	public Documento undo() {
		if (documentoAntigo != null && documentoNovo != null) {
			repositorio.atualizarRepositorio(documentoNovo, documentoAntigo);
			repositorio.setDocumentoAtual(documentoAntigo);
		}
		return documentoAntigo;
	}
	
	@Override
	public Documento redo() {
		if (documentoAntigo != null && documentoNovo != null) {
			repositorio.atualizarRepositorio(documentoAntigo, documentoNovo);
			repositorio.setDocumentoAtual(documentoNovo);
		}
		return documentoNovo;
	}
}