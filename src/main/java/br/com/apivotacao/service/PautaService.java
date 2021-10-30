package br.com.apivotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.repository.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	public void save(Pauta pauta) {
	  pautaRepository.save(pauta);	
	}
}
