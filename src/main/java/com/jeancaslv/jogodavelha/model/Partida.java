package com.jeancaslv.jogodavelha.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PARTIDA")
public class Partida implements Serializable {

	private static final long serialVersionUID = 1539359495748542133L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private String firstPlayer;

	private String proximoJogador;

	public Partida(String firstPlayer, String proximoJogador) {
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

}
