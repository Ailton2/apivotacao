package br.com.apivotacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apivotacao.dto.VotacaoResponseDTO;
import br.com.apivotacao.exception.VotacaoException;
import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.model.Votacao;
import br.com.apivotacao.model.Voto;
import br.com.apivotacao.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Pauta pauta) {
		try {
			pautaService.salvar(pauta);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public ResponseEntity<?> listarPautas() {
		try {
			List<Pauta> pautas = pautaService.listarPautas();
			return ResponseEntity.ok(pautas);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Pauta pauta = pautaService.buscarPautaPorid(id);
			return ResponseEntity.ok(pauta);
		} catch (VotacaoException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/iniciar-votacao")
	public ResponseEntity<?> iniciarVotacaoo(@RequestBody Votacao votacao) {
		try {
			pautaService.iniciaVotacao(votacao);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PostMapping("/votar")
	public ResponseEntity<?> votar(@RequestBody Voto voto) {
		try {
			pautaService.votar(voto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (VotacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/votacao/{id}")
	public ResponseEntity<?> votacaoPorId(@PathVariable Long id) {
		try {
			VotacaoResponseDTO votacao = pautaService.votacaoPorId(id);
			if(votacao == null) {
				return ResponseEntity.noContent().build();
			}else {
				return ResponseEntity.ok(votacao);
			}
		} catch (VotacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
