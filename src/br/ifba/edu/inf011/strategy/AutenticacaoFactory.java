package br.ifba.edu.inf011.strategy;

import java.util.HashMap;
import java.util.Map;

public class AutenticacaoFactory {
	private final Map<Integer, AutenticacaoStrategy> strategies;
	private final AutenticacaoStrategy defaultStrategy;
	
	public AutenticacaoFactory() {
		this.strategies = new HashMap<>();
		this.defaultStrategy = new AutenticadorDefaultStrategy();
		this.register(0, new AutenticadorCriminalStrategy());
		this.register(1, new AutenticadorPessoalStrategy());
		this.register(2, new AutenticadorExportacaoStrategy());
	}
	
	public void register(int tipo, AutenticacaoStrategy strategy) {
		this.strategies.put(tipo, strategy);
	}
	
	public AutenticacaoStrategy getStrategy(int tipo) {
		return this.strategies.getOrDefault(tipo, this.defaultStrategy);
	}
	
}
