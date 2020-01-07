package com.jeancaslv.jogodavelha.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeancaslv.jogodavelha.dto.MovimentoDTO;
import com.jeancaslv.jogodavelha.dto.VencedorDTO;
import com.jeancaslv.jogodavelha.exception.JogoDaVelhaException;
import com.jeancaslv.jogodavelha.exception.PartidaInexistente;
import com.jeancaslv.jogodavelha.exception.TurnoErradoException;
import com.jeancaslv.jogodavelha.service.MovimentoService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("game")
public class MovimentoController {

	private final Logger log = LoggerFactory.getLogger(MovimentoController.class);

	private MovimentoService movimentoService;

	public MovimentoController(MovimentoService movimentoService) {
		this.movimentoService = movimentoService;
	}

	@PostMapping(value = "/{id}/movement")
	public ResponseEntity<VencedorDTO> movimentoJogador(
			@ApiParam(value = "Id da partida a ser jogada.") @PathVariable String id,
			@ApiParam(value = "Dados da partida a ser jogada.") @Valid @RequestBody MovimentoDTO partida) {
		try {

			VencedorDTO vencedorDTO = movimentoService.movimentoPartida(id, partida);
			return new ResponseEntity<VencedorDTO>(vencedorDTO, HttpStatus.OK);

		} catch (TurnoErradoException e) {
			log.error("Erro: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new VencedorDTO(e.getMessage()));
		} catch (PartidaInexistente e) {
			log.error("Erro: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new VencedorDTO(e.getMessage()));
		} catch (JogoDaVelhaException e) {
			log.error("Erro: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new VencedorDTO(e.getMessage()));
		}
	}

}
