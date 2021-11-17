package br.com.apivotacao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.apivotacao.dto.StatusResponse;
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
		
		try {
			if(voto.getCpf_eleitor().isEmpty()) {
				throw new VotacaoException("É necessário informar o CPF..");
			}
			StatusResponse status = validarEleitor(voto.getCpf_eleitor());
			if(status.getStatus().equalsIgnoreCase("ABLE_TO_VOTE")) {
			    if (vtc.get().getDataFim().isBefore(LocalDateTime.now())) {
					throw new VotacaoException("Votação encerrada.");
				}
				if (votos > 0) {
					throw new VotacaoException("Esse eleitor já votou.");
				}
				votoRepository.save(voto);
	        }else {
	        	throw new VotacaoException(status.getStatus());
	        }
		} catch (Exception e) {
			throw new VotacaoException(e.getMessage());
		}
	}
	
	public StatusResponse validarEleitor(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder templateBuilder = new RestTemplateBuilder();
		restTemplate = templateBuilder.build();
		
		StatusResponse status = restTemplate.getForObject("https://user-info.herokuapp.com/users/"+cpf, StatusResponse.class);
		return status;
	}

}
