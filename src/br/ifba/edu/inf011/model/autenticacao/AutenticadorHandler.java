package br.ifba.edu.inf011.model.autenticacao;

import br.ifba.edu.inf011.model.documentos.Documento;

public abstract class AutenticadorHandler {
  protected AutenticadorHandler proximo;
  public static AutenticadorHandler head;

  public AutenticadorHandler () {
    if (head == null) {
      head = this;
    } 
    else {
      this.inserirNoFinal(this);
    }
  }
  
  private void inserirNoFinal(AutenticadorHandler novo) {
    AutenticadorHandler atual = head;

    while (atual.proximo != null) {
      atual = atual.proximo;
    }
    atual.proximo = novo;
  }

  public void autenticar(Documento documento, int tipo) {
    if (this.podeProcessar(tipo)) {
      this.gerarNumero(documento);
    } 
    else if (proximo != null) {
      proximo.autenticar(documento, tipo);
    } 
    else {
      head.gerarNumero(documento);
    }
  }

  protected abstract boolean podeProcessar(int tipo);
  protected abstract void gerarNumero(Documento documento);
}
