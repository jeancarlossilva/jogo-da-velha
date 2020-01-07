package com.jeancaslv.jogodavelha.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class MovimentoDTO implements Serializable {

	private static final long serialVersionUID = 4766765567609990597L;

	@NotBlank
	private String id;
	@NotBlank
	private String player;
	@Valid
	private PosicaoDTO position;

	public MovimentoDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public PosicaoDTO getPosition() {
		return position;
	}

	public void setPosition(PosicaoDTO position) {
		this.position = position;
	}

}
