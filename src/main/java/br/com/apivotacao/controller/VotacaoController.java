package br.com.apivotacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.apivotacao.model.Votacao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface VotacaoController {

	@ApiOperation(value = "Inicia uma votação de uma pauta", 
			      notes = "Inicia uma votação de uma pauta", 
			      nickname = "listaPauta", 
			      response = Votacao.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "votação iniciada com sucesso.", response = Votacao.class), })
	@PostMapping("/iniciar")
	public ResponseEntity<?> iniciarVotacaoo(@RequestBody Votacao votacao);
	
	@ApiOperation(value = "Busca votação por ID e mostra o resultado", 
				  notes = "Busca votação por ID e mostra o resultado", 
				  nickname = "BuscaVotação", 
				  response = Votacao.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "votação encontrada com sucesso.", response = Votacao.class),
			@ApiResponse(code = 204, message = "votação não encontrada.") })
	@GetMapping("/{id}")
	public ResponseEntity<?> votacaoPorId(@PathVariable Long id);
}
