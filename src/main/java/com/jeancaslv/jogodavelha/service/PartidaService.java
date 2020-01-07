package com.jeancaslv.jogodavelha.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.jeancaslv.jogodavelha.dto.PartidaDTO;
import com.jeancaslv.jogodavelha.model.Partida;
import com.jeancaslv.jogodavelha.repository.PartidaRepository;
import com.jeancaslv.jogodavelha.utils.JogoDaVelhaUtils;

@Service
public class PartidaService {

	private PartidaRepository partidaRepository;

	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}

	public PartidaDTO criarPartida() {
		String player = sortearJogador();
		Partida partida = new Partida(player, JogoDaVelhaUtils.proximoJogador(player));
		partidaRepository.save(partida);

		return new PartidaDTO(partida.getId().toString(), partida.getFirstPlayer());
	}

	private String sortearJogador() {
		int sortNumber = new Random().nextInt(2);
		return sortNumber == 1 ? "X" : "Y";
	}

	public Partida findById(String id) {
		return this.partidaRepository.findById(id);
	}

	public void salvar(Partida partida) {
		this.partidaRepository.save(partida);
	}

}
