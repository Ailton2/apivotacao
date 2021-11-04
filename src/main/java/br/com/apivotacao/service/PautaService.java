package br.com.apivotacao.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apivotacao.exception.VotacaoException;
import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.model.Votacao;
import br.com.apivotacao.model.Voto;
import br.com.apivotacao.repository.PautaRepository;
import br.com.apivotacao.repository.VotacaoRepository;
import br.com.apivotacao.repository.VotoRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private VotacaoRepository votacaoRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	public void salvar(Pauta pauta) {
	  pautaRepository.save(pauta);	
	}
	
	public List<Pauta> listarPautas() {
		 return pautaRepository.findAll();	
	}
	
	public void iniciaVotacao(Votacao votacao) {
		Optional<Votacao> v = votacaoRepository.findById(votacao.getPauta().getId());
		
		if(v.isPresent()) {
			throw new VotacaoException("Já existe uma votação para essa pauta.");
		}
		votacaoRepository.save(votacao);
	}
	
	public void votar(Voto voto) {
		Optional<Votacao> vtc = votacaoRepository.findById(voto.getVotacao().getId());
		Integer votos = votoRepository.exiteVoto(voto.getId_eleitor(), voto.getVotacao().getId());

		LocalDateTime tempoDaSessao = vtc.get().getDataFim().plus(1,ChronoUnit.MINUTES);
		if(tempoDaSessao.isBefore(LocalDateTime.now())) {
			throw new VotacaoException("Votação encerrada.");
		}

		if(votos > 0) {
			throw new VotacaoException("Esse eleitor já votou.");
		}

		votoRepository.save(voto);
	}

	public Pauta buscarPorid(Long id) {
		
		Pauta pauta = pautaRepository.findById(id).get();
		if(pauta == null) {
			throw new VotacaoException("Não existe pauta para esse id.");
		}
		return pauta;
	}

	public Votacao votacaoPorId(Long id) {
		 Votacao votacao = votacaoRepository.getById(id);
		 if(votacao == null) {
			throw new VotacaoException("Não existe votação para esse id."); 
		 }
		 return votacao;
	}
	
}
