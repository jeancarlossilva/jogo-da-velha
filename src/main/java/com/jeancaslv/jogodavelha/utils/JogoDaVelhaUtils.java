package com.jeancaslv.jogodavelha.utils;

public class JogoDaVelhaUtils {

	public static String proximoJogador(String player) {
		return player.equals("X") ? "Y" : "X";
	}

}
