package br.com.apivotacao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apivotacao.exception.VotacaoException;
import br.com.apivotacao.model.Votacao;
import br.com.apivotacao.model.Voto;
import br.com.apivotacao.repository.VotacaoRepository;
import br.com.apivotacao.repository.VotoRepository;

@Service
public class VotoService {
	
	@Autowired
	private VotacaoRepository votacaoRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	public void votar(Voto voto) {
		Optional<Votacao> vtc = votacaoRepository.findById(voto.getVotacao().getId());
		Integer votos = votoRepository.exiteVoto(voto.getId_eleitor(), voto.getVotacao().getId());

	    if (vtc.get().getDataFim().isBefore(LocalDateTime.now())) {
			throw new VotacaoException("Votação encerrada.");
		}

		if (votos > 0) {
			throw new VotacaoException("Esse eleitor já votou.");
		}

		votoRepository.save(voto);
	}

}
