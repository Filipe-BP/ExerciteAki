package br.ucs.poo.exerciteaki.exception;

public class AcessoNegadoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Acesso negado.";
	
	public AcessoNegadoException() {
		super(MESSAGE);
	}
	
	public AcessoNegadoException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public AcessoNegadoException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public AcessoNegadoException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
}
