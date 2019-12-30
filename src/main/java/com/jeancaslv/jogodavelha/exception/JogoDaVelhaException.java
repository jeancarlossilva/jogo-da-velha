package com.jeancaslv.jogodavelha.exception;

public class JogoDaVelhaException extends Exception{

	private static final long serialVersionUID = -8658919707116037123L;
	
	private String mensagem;
	
	public JogoDaVelhaException(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}

}
