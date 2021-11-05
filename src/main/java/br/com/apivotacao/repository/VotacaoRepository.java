package br.com.apivotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.apivotacao.dto.VotacaoResponseDTO;
import br.com.apivotacao.model.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

	@Query(" select v from Votacao v where v.pauta.id = :idPauta")
	public VotacaoResponseDTO buscarPorPauta(Long idPauta);
}
