package com.jeancaslv.jogodavelha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeancaslv.jogodavelha.dto.PartidaDTO;
import com.jeancaslv.jogodavelha.service.PartidaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("game")
public class PartidaController {

	private PartidaService partidaService;

	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}

	@ApiOperation(value = "Cria uma partida do jogo da velha.")
	@PostMapping
	public ResponseEntity<PartidaDTO> criaPartida() {
		PartidaDTO partida = this.partidaService.criarPartida();
		return new ResponseEntity<>(partida, HttpStatus.OK);
	}

}
