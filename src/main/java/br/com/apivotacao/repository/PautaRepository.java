package br.com.apivotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apivotacao.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
