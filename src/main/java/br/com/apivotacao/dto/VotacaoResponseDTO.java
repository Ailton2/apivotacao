package br.com.apivotacao.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.apivotacao.model.Pauta;
import br.com.apivotacao.model.Votacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoResponseDTO {

	private Long id;
	
    private LocalDateTime dataFim;;

    private Pauta pauta;
    
    private List<VotoReponseDTO> votos;
    

    
    public VotacaoResponseDTO(Votacao votacao) {
    	this.id = votacao.getId();
    	this.dataFim = votacao.getDataFim();
    	this.pauta = votacao.getPauta();
    	this.votos = votacao.getVotos().stream().map( m -> new VotoReponseDTO(m))
    			.collect(Collectors.toList());
    }
    
	public Map<String, Long> getResultado() {
		return votos.stream().collect(Collectors.groupingBy(VotoReponseDTO::getVoto, Collectors.counting()));

	}
}
