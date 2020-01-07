package com.jeancaslv.jogodavelha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jeancaslv.jogodavelha.model.Movimento;
import com.jeancaslv.jogodavelha.model.Partida;

public interface MovimentoRepository extends JpaRepository<Movimento, Long>, JpaSpecificationExecutor<Movimento> {

	List<Movimento> findByPartida(Partida partida);

}
