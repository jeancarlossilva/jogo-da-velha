package com.jeancaslv.jogodavelha.exception;

public class TurnoErradoException extends Exception {

	private static final long serialVersionUID = 5649595911057416288L;
	
	private static final String TURNO_ERRADO = "Não é o turno do jogador";
	
	@Override
	public String getMessage() {
		return TURNO_ERRADO;
	}
}
