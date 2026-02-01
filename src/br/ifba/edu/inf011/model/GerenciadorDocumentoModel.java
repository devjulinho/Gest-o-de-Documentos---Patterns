package br.ifba.edu.inf011.model;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.model.autenticacao.InicializadorChain;
import br.ifba.edu.inf011.model.documentos.Documento;

public class GerenciadorDocumentoModel {

	private static GerenciadorDocumentoModel instance;
	private List<Documento> repositorio;
    private Documento atual;
    
    private GerenciadorDocumentoModel() {
        this.repositorio = new ArrayList<>();
        this.atual = null;
        InicializadorChain.inicializar();
    }
    
    public static GerenciadorDocumentoModel getInstance() {
        if (instance == null) {
        	instance = new GerenciadorDocumentoModel();
        }
    	return instance;
    }

    public List<Documento> getRepositorio() {
        return repositorio;
    }
    
    public void addDocumento(Documento documento) {
        this.repositorio.add(documento);
    }
    
    public void atualizarRepositorio(Documento antigo, Documento novo) {
        int index = repositorio.indexOf(antigo);
        if (index != -1) {
            repositorio.set(index, novo);
        }
    } 
    
	public Documento getDocumentoAtual() {
		return this.atual;
	}
	
	public void setDocumentoAtual(Documento doc) {
		this.atual = doc;
	}        
    
    
}
