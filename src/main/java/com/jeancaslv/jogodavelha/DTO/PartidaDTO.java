package com.jeancaslv.jogodavelha.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class PartidaDTO implements Serializable {

	private static final long serialVersionUID = 4766765567609990597L;
	
	@NotBlank
	private String id;
	@NotBlank
	private String player;
	@NotBlank
	private PosicaoDTO position;
	
	public PartidaDTO() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PosicaoDTO getPosition() {
		return position;
	}
	public void setPosition(PosicaoDTO position) {
		this.position = position;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	
	
}
