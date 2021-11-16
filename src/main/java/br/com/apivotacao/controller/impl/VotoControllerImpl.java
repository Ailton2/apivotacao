package br.com.apivotacao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apivotacao.controller.VotoController;
import br.com.apivotacao.exception.VotacaoException;
import br.com.apivotacao.model.Voto;
import br.com.apivotacao.service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoControllerImpl implements VotoController {
	
	@Autowired
	private VotoService votoService;

	@Override
	public ResponseEntity<?> votar(Voto voto) {
		try {
			votoService.votar(voto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (VotacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
