package br.com.apivotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Pauta pauta){
		pautaService.save(pauta);
		return ResponseEntity.ok().build();
	}
}
