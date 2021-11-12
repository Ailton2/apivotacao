package br.com.apivotacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apivotacao.exception.PautaException;
import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.repository.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	public void salvar(Pauta pauta) {
		pautaRepository.save(pauta);
	}

	public List<Pauta> listarPautas() {
		return pautaRepository.findAll();
	}

	public Pauta buscarPautaPorid(Long id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		if (!pauta.isPresent()) {
			throw new PautaException("Não existe pauta para esse id.");
		}
		return pauta.get();
	}

}
