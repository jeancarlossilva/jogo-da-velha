package com.jeancaslv.jogodavelha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeancaslv.jogodavelha.dto.MovimentoDTO;
import com.jeancaslv.jogodavelha.dto.VencedorDTO;
import com.jeancaslv.jogodavelha.exception.JogoDaVelhaException;
import com.jeancaslv.jogodavelha.exception.PartidaInexistente;
import com.jeancaslv.jogodavelha.exception.TurnoErradoException;
import com.jeancaslv.jogodavelha.model.Movimento;
import com.jeancaslv.jogodavelha.model.Partida;
import com.jeancaslv.jogodavelha.model.Posicao;
import com.jeancaslv.jogodavelha.repository.MovimentoRepository;
import com.jeancaslv.jogodavelha.utils.JogoDaVelhaUtils;

@Service
public class MovimentoService {

	private PartidaService partidaService;

	private MovimentoRepository movimentoRepository;

	public MovimentoService(PartidaService partidaService, MovimentoRepository movimentoRepository) {
		this.partidaService = partidaService;
		this.movimentoRepository = movimentoRepository;
	}

	public VencedorDTO movimentoPartida(String id, MovimentoDTO movimentoDTO)
			throws PartidaInexistente, TurnoErradoException, JogoDaVelhaException {

		Partida partidaSalva = this.partidaService.findById(id);
		verificaPartidaInexistente(partidaSalva);
		String player = movimentoDTO.getPlayer().toUpperCase();
		verificaTurnoErrado(partidaSalva, player);
		validaMovimento(movimentoDTO, partidaSalva);

		atualizarPartida(partidaSalva);
		Movimento movimento = createMovimento(movimentoDTO, partidaSalva);
		this.movimentoRepository.save(movimento);

		List<Movimento> movimentos = this.movimentoRepository.findByPartida(partidaSalva);
		String[][] tabuleiro = construirTabuleiro(movimentos);

		String vencedor = verificarVencedor(tabuleiro);

		switch (vencedor) {
		case "X":
			return new VencedorDTO("Partida finalizada", vencedor);
		case "Y":
			return new VencedorDTO("Partida finalizada", vencedor);
		case "EMPATE":
			return new VencedorDTO("Partida finalizada", "Draw");
		default:
			return new VencedorDTO("Jogada realizada");
		}

	}

	private void validaMovimento(MovimentoDTO movimentoDTO, Partida partidaSalva) throws JogoDaVelhaException {
		int x = movimentoDTO.getPosition().getX();
		int y = movimentoDTO.getPosition().getY();
		List<Movimento> movimentos = this.movimentoRepository.findByPartida(partidaSalva);
		String[][] tabuleiro = construirTabuleiro(movimentos);
		validarPosicao(tabuleiro, x, y);
	}

	private void atualizarPartida(Partida partidaSalva) {
		partidaSalva.setProximoJogador(JogoDaVelhaUtils.proximoJogador(partidaSalva.getProximoJogador()));

		this.partidaService.salvar(partidaSalva);
	}

	private void verificaTurnoErrado(Partida partidaSalva, String player) throws TurnoErradoException {
		if (partidaSalva.getProximoJogador().equals(player)) {
			throw new TurnoErradoException();
		}
	}

	private void verificaPartidaInexistente(Partida partidaSalva) throws PartidaInexistente {
		if (partidaSalva == null) {
			throw new PartidaInexistente();
		}
	}

	private String verificarVencedor(String[][] tabuleiro) {
		for (int i = 0; i < 3; i++) {
			if ((tabuleiro[i][0] != null && tabuleiro[i][0].equals(tabuleiro[i][1]))
					&& (tabuleiro[i][0] != null && tabuleiro[i][0].equals(tabuleiro[i][2]))) {
				if (tabuleiro[i][0] != null) {
					return tabuleiro[i][0];
				}
			}
		}
		for (int j = 0; j < 3; j++) {
			if ((tabuleiro[0][j] != null && tabuleiro[0][j].equals(tabuleiro[1][j]))
					&& (tabuleiro[0][j] != null && tabuleiro[0][j].equals(tabuleiro[2][j]))
					&& (tabuleiro[0][j] != null)) {
				return tabuleiro[0][j];
			}
		}
		if ((tabuleiro[0][0] != null && tabuleiro[0][0].equals(tabuleiro[1][1]))
				&& (tabuleiro[0][0] != null && tabuleiro[0][0].equals(tabuleiro[2][2])) && (tabuleiro[0][0] != null)) {
			return tabuleiro[0][0];
		}
		if ((tabuleiro[0][2] != null && tabuleiro[0][2].equals(tabuleiro[1][1]))
				&& (tabuleiro[0][2] != null && tabuleiro[0][2].equals(tabuleiro[2][0])) && (tabuleiro[0][2] != null)) {
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

	private void validarPosicao(String[][] tabuleiro, int x, int y) throws JogoDaVelhaException {

		if ((x < 0) || (x > 2) || (y < 0) || (y > 2)) {
			throw new JogoDaVelhaException("Posição inexistente");
		}

		if (tabuleiro[x][y] != null) {
			throw new JogoDaVelhaException("Posição ocupada");
		}
	}

	private Movimento createMovimento(MovimentoDTO movimentoDTO, Partida partidaSalva) {

		Movimento movimento = new Movimento();
		movimento.setPlayer(movimentoDTO.getPlayer());
		movimento.setPosicao(new Posicao(movimentoDTO.getPosition().getX(), movimentoDTO.getPosition().getY()));
		movimento.setPartida(partidaSalva);
		return movimento;
	}

	private String[][] construirTabuleiro(List<Movimento> movimentos) {

		String[][] tabuleiro = new String[3][3];
		for (Movimento movimento : movimentos) {
			int x = movimento.getPosicao().getX();
			int y = movimento.getPosicao().getY();
			tabuleiro[x][y] = movimento.getPlayer().toUpperCase();
		}

		return tabuleiro;
	}

}
