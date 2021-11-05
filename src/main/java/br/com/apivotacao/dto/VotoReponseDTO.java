package br.com.apivotacao.dto;

import br.com.apivotacao.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoReponseDTO {

    private Long id;
	private String voto;
	private Long id_eleitor;
	
	public VotoReponseDTO(Voto voto) {
		this.id = voto.getId();
		this.voto = voto.getVoto();
		this.id_eleitor = voto.getId_eleitor();
	}
}
