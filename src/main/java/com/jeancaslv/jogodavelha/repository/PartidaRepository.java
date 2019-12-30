package com.jeancaslv.jogodavelha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jeancaslv.jogodavelha.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>, JpaSpecificationExecutor<Partida>{

	Partida findById(String id);

}
