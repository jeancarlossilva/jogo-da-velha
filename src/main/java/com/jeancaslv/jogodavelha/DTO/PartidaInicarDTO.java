package com.jeancaslv.jogodavelha.DTO;

import java.io.Serializable;

public class PartidaInicarDTO implements Serializable {

	private static final long serialVersionUID = 5856495277225312154L;
	
	private String id;
	private String firstPlayer;
	
	public PartidaInicarDTO(String id, String firstPlayer) {
		this.id = id;
		this.firstPlayer = firstPlayer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(String firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	
	
}
