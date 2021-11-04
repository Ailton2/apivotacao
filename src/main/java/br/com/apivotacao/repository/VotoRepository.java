package br.com.apivotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.apivotacao.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{

	@Query("select count(v) from Voto v where v.id_eleitor = :idEleitor and v.votacao.id = :idVotacao")
	public Integer exiteVoto(Long idEleitor, Long idVotacao);
}
