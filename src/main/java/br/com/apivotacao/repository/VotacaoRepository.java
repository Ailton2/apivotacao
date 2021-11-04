package br.com.apivotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apivotacao.model.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

}
