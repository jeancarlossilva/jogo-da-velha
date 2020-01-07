package com.jeancaslv.jogodavelha.dto;

import java.io.Serializable;

public class VencedorDTO implements Serializable {

	private static final long serialVersionUID = 1707962929720857447L;

	private String msg;
	private String winner;

	public VencedorDTO(String msg, String winner) {
		super();
		this.msg = msg;
		this.winner = winner;
	}

	public VencedorDTO(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

}
