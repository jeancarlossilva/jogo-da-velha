package com.jeancaslv.jogodavelha.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Posicao")
public class Posicao implements Serializable {

	private static final long serialVersionUID = 2369674375758375724L;

	@Id
	@GeneratedValue
	private Integer id;
	private Integer x;
	private Integer y;
	@OneToOne(mappedBy = "posicao")
	private Movimento movimento;

	public Posicao(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Posicao() {
		super();
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}

}
