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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@ApiOperation(
			value = "Persiste uma Pauta",
			notes = "Persiste uma Pauta",
			nickname = "salvaPauta",
			response = Pauta.class
	)
	@ApiResponses({
			@ApiResponse(code = 201, message = "Pauta salva com sucesso.",response = Pauta.class),
	})
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Pauta pauta) {
		try {
			pautaService.salvar(pauta);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@ApiOperation(
			value = "Retorna lista de Pautas",
			notes = "Retorna lista de Pautas",
			nickname = "listaPauta",
			response = Pauta.class
	)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Pautas retornadas com sucesso.",response = Pauta.class),
	})
	@GetMapping
	public ResponseEntity<?> listarPautas() {
		try {
			List<Pauta> pautas = pautaService.listarPautas();
			return ResponseEntity.ok(pautas);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@ApiOperation(
			value = "Retorna uma Pauta por ID",
			notes = "Retorna uma Pauta por ID",
			nickname = "buscaPauta",
			response = Pauta.class
	)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Pauta encontrada com sucesso.",response = Pauta.class),
			@ApiResponse(code = 204, message = "Pauta não encontrada.")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Pauta pauta = pautaService.buscarPautaPorid(id);
			return ResponseEntity.ok(pauta);
		} catch (VotacaoException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(
			value = "Inicia uma votação de uma pauta",
			notes = "Inicia uma votação de uma pauta",
			nickname = "listaPauta",
			response = Votacao.class
	)
	@ApiResponses({
			@ApiResponse(code = 201, message = "votação iniciada com sucesso.",response = Votacao.class),
	})
	@PostMapping("/iniciar-votacao")
	public ResponseEntity<?> iniciarVotacaoo(@RequestBody Votacao votacao) {
		try {
			pautaService.iniciaVotacao(votacao);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@ApiOperation(
			value = "Realiza o Voto",
			notes = "Realiza o Voto",
			nickname = "listaPauta",
			response = Voto.class
	)
	@ApiResponses({
			@ApiResponse(code = 201, message = "Voto realizado com sucesso.",response = Voto.class),
	})
	@PostMapping("/votar")
	public ResponseEntity<?> votar(@RequestBody Voto voto) {
		try {
			pautaService.votar(voto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (VotacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(
			value = "Busca votação por ID e mostra o resultado",
			notes = "Busca votação por ID e mostra o resultado",
			nickname = "BuscaVotação",
			response = Votacao.class
	)
	@ApiResponses({
			@ApiResponse(code = 200, message = "votação encontrada com sucesso.",response = Votacao.class),
			@ApiResponse(code = 204, message = "votação não encontrada.")
	})
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
