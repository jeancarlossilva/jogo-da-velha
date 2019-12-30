package com.jeancaslv.jogodavelha.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class PosicaoDTO implements Serializable{

	private static final long serialVersionUID = 3365672688734156353L;
	
	@NotNull
	private int x;
	@NotNull
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
