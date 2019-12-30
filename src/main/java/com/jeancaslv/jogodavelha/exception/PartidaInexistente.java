package com.jeancaslv.jogodavelha.exception;

public class PartidaInexistente extends Exception{

	private static final long serialVersionUID = 484004718448096764L;
	
	private static final String PARTIDA_INEXISTENTE = "Partida não encontrada";
	
	@Override
	public String getMessage() {
		return PARTIDA_INEXISTENTE;
	}

}
