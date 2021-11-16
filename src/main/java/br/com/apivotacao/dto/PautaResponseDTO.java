package br.com.apivotacao.dto;

import java.util.Map;

import br.com.apivotacao.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponseDTO {
	
    private Long id;

    private String titulo;

    private String descricao;
    
    private Map<String, Long> resultado;
    
    public PautaResponseDTO(Pauta pauta) {
         this.id = pauta.getId();
         this.titulo = pauta.getTitulo();
         this.titulo = pauta.getDescricao();
	}

}
