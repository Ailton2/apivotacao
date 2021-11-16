package br.com.apivotacao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apivotacao.controller.VotacaoController;
import br.com.apivotacao.dto.VotacaoResponseDTO;
import br.com.apivotacao.exception.VotacaoException;
import br.com.apivotacao.model.Votacao;
import br.com.apivotacao.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class VotacaoControllerImpl implements VotacaoController {
	
	@Autowired
	private VotacaoService votacaoService;

	@Override
	public ResponseEntity<?> iniciarVotacaoo(Votacao votacao) {
		try {
			votacaoService.iniciaVotacao(votacao);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (VotacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> votacaoPorId(Long id) {
		try {
			VotacaoResponseDTO votacao = votacaoService.votacaoPorId(id);
			return ResponseEntity.ok(votacao);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

}
