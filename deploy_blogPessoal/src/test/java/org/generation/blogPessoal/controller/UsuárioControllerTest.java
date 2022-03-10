package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuárioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@Order(1)
	public void deveCadastrarUmUsuario() {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, "Zé Mario", "zemario@ovo.com", "ovofrito123", "https://d5y9g7a5.rocketcdn.me/wp-content/uploads/2021/02/ovo-frito-como-prepara-lo-de-forma-mais-saudavel-sem-perder-o-sabor-1.jpg.webp"));
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		 
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
		
		
	}
	
	@Test
	@Order(2)
	private void deveAtualizarUmUsuario() {
		Optional<Usuario> usuarioCreate = usuarioService.CadastrarUsuario(new Usuario(0L,"TinkWink", "tinkwink@telletubies.com.br", "bolsavermelha", "https://pbs.twimg.com/profile_images/3457438261/e839142b1e74a6c69ce06189edf5a4e7_400x400.jpeg"));
		
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),"TinkWink", "tinkwink@telletubies.com.br", "bolsavermelha", "https://pbs.twimg.com/profile_images/3457438261/e839142b1e74a6c69ce06189edf5a4e7_400x400.jpeg");
		
		HttpEntity<Usuario> requisicaoAtualizacao = new HttpEntity<Usuario>(usuarioUpdate);
		
		ResponseEntity<Usuario> respostaAtualizacao = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicaoAtualizacao, Usuario.class);
		
		
		assertEquals(HttpStatus.OK, respostaAtualizacao.getStatusCode());
	}

}
