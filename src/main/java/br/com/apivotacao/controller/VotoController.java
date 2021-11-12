package br.com.apivotacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.apivotacao.model.Voto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface VotoController {
	
	@ApiOperation(value = "Realiza o Voto", 
			      notes = "Realiza o Voto", 
			      nickname = "listaPauta", 
			      response = Voto.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Voto realizado com sucesso.", response = Voto.class), })
	@PostMapping("/votar")
	public ResponseEntity<?> votar(@RequestBody Voto voto);
}
