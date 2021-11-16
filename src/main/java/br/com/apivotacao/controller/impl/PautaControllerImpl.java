package br.com.apivotacao.controller.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apivotacao.controller.PautaController;
import br.com.apivotacao.dto.PautaResponseDTO;
import br.com.apivotacao.dto.VotacaoResponseDTO;
import br.com.apivotacao.dto.VotoReponseDTO;
import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.service.PautaService;
import br.com.apivotacao.service.VotacaoService;

@RestController
@RequestMapping("/pauta")
public class PautaControllerImpl implements PautaController {

	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private VotacaoService votacaoService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<PautaResponseDTO> salvar(Pauta pauta) {
		try {
			pautaService.salvar(pauta);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<?> buscarPorId(Long idPauta) {
		try {
			Pauta pauta = pautaService.buscarPautaPorid(idPauta);
			PautaResponseDTO pautaDTO = modelMapper.map(pauta, PautaResponseDTO.class);
			pautaDTO.setResultado(resultado(idPauta));
			return ResponseEntity.ok(pautaDTO);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public ResponseEntity<?> listarPautas() {
		List<Pauta> pautas = pautaService.listarPautas();
		return ResponseEntity.ok(pautas);
	}
	

	public Map<String, Long> resultado(Long idPauta) {
		VotacaoResponseDTO votacao = votacaoService.votacaoPorId(idPauta);
		return votacao.getVotos()
				.stream()
				.collect(Collectors.groupingBy(VotoReponseDTO::getVoto, Collectors.counting()));

	}
}
