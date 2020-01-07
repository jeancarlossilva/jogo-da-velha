package com.jeancaslv.jogodavelha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jeancaslv.jogodavelha.model.Posicao;

public interface PosicaoRepository extends JpaRepository<Posicao, Long>, JpaSpecificationExecutor<Posicao> {

}
