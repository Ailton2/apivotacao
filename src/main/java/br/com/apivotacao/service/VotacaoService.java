package br.com.apivotacao.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apivotacao.dto.VotacaoResponseDTO;
import br.com.apivotacao.exception.VotacaoException;
import br.com.apivotacao.model.Votacao;
import br.com.apivotacao.repository.VotacaoRepository;

@Service
public class VotacaoService {
	
	@Autowired
	private VotacaoRepository votacaoRepository;
	
	public void iniciaVotacao(Votacao votacao) {
		Optional<Votacao> v = votacaoRepository.findById(votacao.getPauta().getId());

		if (v.isPresent()) {
			throw new VotacaoException("Já existe uma votação para essa pauta.");
		}
		LocalDateTime tempoDaSessao = votacao.getDataFim().plus(1, ChronoUnit.MINUTES);
		votacao.setDataFim(tempoDaSessao);
		votacaoRepository.save(votacao);
	}
	
	public VotacaoResponseDTO votacaoPorId(Long id) {
		VotacaoResponseDTO votacao = votacaoRepository.buscarPorPauta(id);
		if (votacao == null) {
			throw new VotacaoException("Não existe votação para essa Pauta.");
		}
		return votacao;
	}

}
