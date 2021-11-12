package br.com.apivotacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.apivotacao.model.Pauta;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface PautaController {
	
	@ApiOperation(value = "Persiste uma Pauta", 
			      notes = "Persiste uma Pauta", 
			      nickname = "salvaPauta", 
			      response = Pauta.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Pauta salva com sucesso.", response = Pauta.class), })
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Pauta pauta);

	@ApiOperation(value = "Retorna lista de Pautas", 
			      notes = "Retorna lista de Pautas", 
			      nickname = "listaPauta", 
			      response = Pauta.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Pautas retornadas com sucesso.", response = Pauta.class), })
	@GetMapping
	public ResponseEntity<?> listarPautas();

	@ApiOperation(value = "Retorna uma Pauta por ID", 
			      notes = "Retorna uma Pauta por ID", 
			      nickname = "buscaPauta", 
			      response = Pauta.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Pauta encontrada com sucesso.", response = Pauta.class),
			@ApiResponse(code = 204, message = "Pauta não encontrada.") })
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id);



	


}
