package br.com.apivotacao;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.apivotacao.controller.PautaControllerImpl;
import io.restassured.http.ContentType;

@SpringBootTest
public class PautaControllerTest {
	
	@Autowired
    private PautaControllerImpl pautaController;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pautaController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarListaDePautas() {
		
		 given()
		.accept(ContentType.JSON)
		.when().get("/pauta").then()
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPautaPorId() {
		
		 given()
		.accept(ContentType.JSON)
		.when().get("/pauta/{id}",4L).then()
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPautaInexistente() {
		
		 given()
		.accept(ContentType.JSON)
		.when().get("/pauta/{id}",7L).then()
		.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoSalvaPauta() {
		
		 given()
		.body("{ \"titulo\": \"teste\" }")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when().post("/pauta").then()
		.statusCode(HttpStatus.CREATED.value());
	}

}
