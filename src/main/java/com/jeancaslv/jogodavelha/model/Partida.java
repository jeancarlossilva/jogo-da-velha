package com.jeancaslv.jogodavelha.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARTIDA")
public class Partida implements Serializable{
	
	private static final long serialVersionUID = 1539359495748542133L;

	@Id
	private String id;
	
	private String firstPlayer;
	
	private String proximoJogador;
	
	private String[][] tabuleiro = new String[3][3];
	
	public Partida(String id, String firstPlayer, String proximoJogador) {
		this.id = id;
		this.firstPlayer = firstPlayer;
		this.proximoJogador = proximoJogador;
	}

	public Partida() {
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

	public String getProximoJogador() {
		return proximoJogador;
	}

	public void setProximoJogador(String proximoJogador) {
		this.proximoJogador = proximoJogador;
	}

	public String[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(String[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	

}
