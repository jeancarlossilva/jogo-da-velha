package com.jeancaslv.jogodavelha.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeancaslv.jogodavelha.DTO.PartidaDTO;
import com.jeancaslv.jogodavelha.DTO.PartidaInicarDTO;
import com.jeancaslv.jogodavelha.DTO.VencedorDTO;
import com.jeancaslv.jogodavelha.exception.JogoDaVelhaException;
import com.jeancaslv.jogodavelha.exception.PartidaInexistente;
import com.jeancaslv.jogodavelha.exception.TurnoErradoException;
import com.jeancaslv.jogodavelha.service.JogoDaVelhaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class JogoDaVelhaController {
	
	private final Logger log = LoggerFactory.getLogger(JogoDaVelhaController.class);
	
	private JogoDaVelhaService jogoDaVelhaService;
	
	public JogoDaVelhaController(JogoDaVelhaService jogoDaVelhaService) {
		this.jogoDaVelhaService = jogoDaVelhaService;
	}
	
	@ApiOperation(value = "Cria uma partida do jogo da velha.")
	@PostMapping(value = "/game")
	public ResponseEntity<PartidaInicarDTO>  criaPartida() {
		PartidaInicarDTO partida = this.jogoDaVelhaService.criarPartida();
		return new ResponseEntity<>(partida, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/game/{id}/movement")
	public ResponseEntity<VencedorDTO> movimentoJogador(
			 @ApiParam(value = "Id da partida a ser jogada.")@PathVariable String id,
			 @ApiParam(value = "Dados da partida a ser jogada.")@Valid @RequestBody PartidaDTO partida) {
		try {
			
			VencedorDTO vencedorDTO =  jogoDaVelhaService.movimentoPartida(id, partida);
			return new  ResponseEntity<VencedorDTO>(vencedorDTO, HttpStatus.OK);
			
		} catch (TurnoErradoException e) {
			log.error("Erro: " + e.getMessage());
			return (ResponseEntity<VencedorDTO>) ResponseEntity.badRequest().header("Jogo da Velha",e.getMessage());
		} catch (PartidaInexistente e) {
			log.error("Erro: " + e.getMessage());
			return (ResponseEntity<VencedorDTO>) ResponseEntity.badRequest().header("Jogo da Velha",e.getMessage());
		} catch (JogoDaVelhaException e) {
			log.error("Erro: " + e.getMessage());
			return (ResponseEntity<VencedorDTO>) ResponseEntity.badRequest().header("Jogo da Velha",e.getMessage());
		}
	}

}
