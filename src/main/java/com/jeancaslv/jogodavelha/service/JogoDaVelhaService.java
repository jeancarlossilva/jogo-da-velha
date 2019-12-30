package com.jeancaslv.jogodavelha.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jeancaslv.jogodavelha.DTO.PartidaDTO;
import com.jeancaslv.jogodavelha.DTO.PartidaInicarDTO;
import com.jeancaslv.jogodavelha.DTO.VencedorDTO;
import com.jeancaslv.jogodavelha.exception.JogoDaVelhaException;
import com.jeancaslv.jogodavelha.exception.PartidaInexistente;
import com.jeancaslv.jogodavelha.exception.TurnoErradoException;
import com.jeancaslv.jogodavelha.model.Partida;
import com.jeancaslv.jogodavelha.repository.PartidaRepository;

@Service
public class JogoDaVelhaService {
	
	private PartidaRepository partidaRepository;
	
	public JogoDaVelhaService(PartidaRepository partidaRepository ) {
		this.partidaRepository = partidaRepository;
	}

	public PartidaInicarDTO criarPartida() {
		UUID uuid = UUID.randomUUID();
		String player = sortearJogador();
		Partida partida = new Partida(uuid.toString(), player, proximoJogador(player));
		partidaRepository.save(partida);
		
		return new PartidaInicarDTO(partida.getId(), partida.getFirstPlayer());
	}
	
	public String sortearJogador() {
		String letras = "XY";
		String armazenaChaves = "";
		int index = -1;
		Random random = new Random();
		 
		for( int i = 0; i < 1; i++ ) {
		   index = random.nextInt( letras.length() );
		   armazenaChaves += letras.substring( index, index + 1 );
		}
		
		return armazenaChaves;
		
	}
	
	public VencedorDTO movimentoPartida(String id, PartidaDTO partida) throws PartidaInexistente, TurnoErradoException, JogoDaVelhaException {
		
		
		Partida partidaSalva = this.partidaRepository.findById(id);
		verificaPartidaInexistente(partidaSalva);
		String[][] tabuleiro = partidaSalva.getTabuleiro();
		int x = partida.getPosition().getX();
		int y = partida.getPosition().getY();
		validaJogada(tabuleiro, x, y);
		
		String player = partida.getPlayer().toUpperCase();
		verificaTurnoErrado(partidaSalva, player);
		
		tabuleiro[x][y] = player;
		partidaSalva.setTabuleiro(tabuleiro);
		partidaSalva.setProximoJogador(proximoJogador(partidaSalva.getProximoJogador()));
		this.partidaRepository.save(partidaSalva);
		
		String vencedor = verificarVencedor(tabuleiro);
		
		switch(vencedor) {
			case "X":
				return new VencedorDTO("Partida finalizada", vencedor);
			case "Y": 
				return new VencedorDTO("Partida finalizada", vencedor);
			case "EMPATE": 
				return new VencedorDTO("Partida finalizada", "Draw");
		} 
		
		
		return null;
		
	}

	private String proximoJogador(String player) {
		return player == "X"? "Y" : "X";
	}

	private void verificaTurnoErrado(Partida partidaSalva, String player) throws TurnoErradoException {
		if(partidaSalva.getProximoJogador().equals(player)) {
			throw new TurnoErradoException();
		}
	}

	private void verificaPartidaInexistente(Partida partidaSalva) throws PartidaInexistente {
		if(partidaSalva == null) {
			throw new PartidaInexistente();
		}
	}
	
	public String verificarVencedor(String[][] tabuleiro) {
		for (int i = 0; i < 3; i++) {
			if ((tabuleiro[i][0] != null && tabuleiro[i][0].equals(tabuleiro[i][1])) &&
				(tabuleiro[i][0] != null && tabuleiro[i][0].equals(tabuleiro[i][2]))) {
				if (tabuleiro[i][0]!= null) {
					return tabuleiro[i][0];
				}
			}
		}
		for (int j = 0; j < 3; j++) {
			if ((tabuleiro[0][j] != null && tabuleiro[0][j].equals(tabuleiro[1][j])) &&
				(tabuleiro[0][j] != null && tabuleiro[0][j].equals(tabuleiro[2][j])) &&
				(tabuleiro[0][j]!= null)) {
				return tabuleiro[0][j];
			}
		}
		if ((tabuleiro[0][0] != null && tabuleiro[0][0].equals(tabuleiro[1][1])) &&
			(tabuleiro[0][0] != null && tabuleiro[0][0].equals(tabuleiro[2][2])) &&
			(tabuleiro[0][0] != null)) {
			return tabuleiro[0][0];
		}
		if ((tabuleiro[0][2] != null && tabuleiro[0][2].equals(tabuleiro[1][1])) &&
			(tabuleiro[0][2] != null && tabuleiro[0][2].equals(tabuleiro[2][0])) &&
			(tabuleiro[0][2] != null)) {
			return tabuleiro[0][2];
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == null) {
					return "";
				}
			}
		}
		return "EMPATE";
	}
	
	private void validaJogada(String[][] tabuleiro, int x, int y) throws JogoDaVelhaException {
		
		if ((x < 0) || (x > 2) || (y < 0) || (y > 2)) {
			throw new JogoDaVelhaException("Posição inexistente");
		}
		
		if (tabuleiro[x][y] != null) {
			throw new JogoDaVelhaException("Posição ocupada");
		}
	}
	
}
