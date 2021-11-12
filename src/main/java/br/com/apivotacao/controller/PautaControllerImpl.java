package br.com.apivotacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaControllerImpl implements PautaController {

	@Autowired
	private PautaService pautaService;

	@Override
	public ResponseEntity<?> salvar(Pauta pauta) {
		try {
			pautaService.salvar(pauta);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<?> buscarPorId(Long id) {
		try {
			Pauta pauta = pautaService.buscarPautaPorid(id);
			return ResponseEntity.ok(pauta);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public ResponseEntity<?> listarPautas() {
		List<Pauta> pautas = pautaService.listarPautas();
		return ResponseEntity.ok(pautas);
	}

}
