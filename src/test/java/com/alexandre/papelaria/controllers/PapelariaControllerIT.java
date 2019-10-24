package com.alexandre.papelaria.controllers;

import com.alexandre.papelaria.PapelariaApplicationTests;
import com.alexandre.papelaria.dao.ProdutoDAO;
import com.alexandre.papelaria.models.Categoria;
import com.alexandre.papelaria.models.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PapelariaControllerIT extends PapelariaApplicationTests {

	private MockMvc mockMvc;

	private final String BASE_URL = "/produtos";

	private ObjectMapper objMapper;

	@MockBean
	private ProdutoDAO mockProdRepository;

	@Autowired
	private PapelariaController papelariaController;

	@Before
	public void setUp() {
		objMapper = new ObjectMapper();
		this.mockMvc = MockMvcBuilders.standaloneSetup(papelariaController).build();
	}

	@Test
	public void testGetIndexPapelariaController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/produtos")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void exibirProduto_200() throws Exception{
		Produto produto = new Produto();
		produto.setBarcode(1);
		produto.setNome("teste");
		produto.setDescricao("teste");
		produto.setQuantidade(1);
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setDescricao("informatica");
		
		produto.setCategoria(categoria);
	
	when(mockProdRepository.exibirProduto(produto.getBarcode())).thenReturn(produto);
	mockMvc.perform(get(BASE_URL + "/exibirproduto/1"))
	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.barcode",is(1)))
    .andExpect(jsonPath("$.nome",is("teste")))
    .andExpect(jsonPath("$.descricao",is("teste")))
    .andExpect(jsonPath("$.quantidade",is(1)))
    .andExpect(jsonPath("$.categoria.id",is(1)))
    .andExpect(jsonPath("$.categoria.descricao",is("informatica")));
	
	verify(mockProdRepository,times(1)).exibirProduto(1);
	
	}
	
	@Test
	public void exibirProduto_404() throws Exception{
		mockMvc.perform(get(BASE_URL + "/2")).andExpect(status().isNotFound());
	}
	
	@Test
	public void cadastrarProduto_200() throws Exception{
		Produto produto = new Produto();
		produto.setBarcode(1);
		produto.setNome("teste");
		produto.setDescricao("teste");
		produto.setQuantidade(1);
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setDescricao("informatica");
		
		produto.setCategoria(categoria);
		
		//when(mockProdRepository.cadastrarProduto(produto)).thenReturn(status().isOk());
		
		mockMvc.perform(post(BASE_URL + "/cadastrarprodutos")
                .content(objMapper.writeValueAsString(produto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.barcode", is(1)))
                .andExpect(jsonPath("$.nome", is("Teste")))
                .andExpect(jsonPath("$.descricao",is("teste")))
                .andExpect(jsonPath("$.quantidade",is(1)))
                .andExpect(jsonPath("$.categoria.id",is(1)))
                .andExpect(jsonPath("$.categoria.descricao",is("informatica")))
                .andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(mockProdRepository,times(1)).cadastrarProduto(produto);
		
	}
	
	@Test
	public void atualizarProduto_200() throws Exception{
		Produto produto = new Produto();
		produto.setBarcode(1);
		produto.setNome("teste");
		produto.setDescricao("teste");
		produto.setQuantidade(1);
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setDescricao("informatica");
		
		produto.setCategoria(categoria);
		
		when(mockProdRepository.exibirProduto(produto.getBarcode())).thenReturn(produto);
		
		mockMvc.perform(put(BASE_URL + "/atualizarproduto")
                .content(objMapper.writeValueAsString(produto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.barcode", is(1)))
                .andExpect(jsonPath("$.nome", is("Teste")))
                .andExpect(jsonPath("$.descricao",is("teste")))
                .andExpect(jsonPath("$.quantidade",is(1)))
                .andExpect(jsonPath("$.categoria.id",is(1)))
                .andExpect(jsonPath("$.categoria.descricao",is("informatica")))
                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void removerProduto_200() throws Exception{
		Produto produto = new Produto();
		produto.setBarcode(1);
		
		when(mockProdRepository.exibirProduto(produto.getBarcode())).thenReturn(produto);
		
		mockMvc.perform(delete(BASE_URL + "removerproduto/1"))
        .andExpect(status().isOk());

		verify(mockProdRepository, times(1)).removerProduto(1);
	}
	
	


	

}
