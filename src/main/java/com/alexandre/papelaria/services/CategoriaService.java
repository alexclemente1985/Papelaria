package com.alexandre.papelaria.services;

import java.util.List;

import com.alexandre.papelaria.models.Categoria;

public interface CategoriaService {
	public List<Categoria> listarCategorias();

	public void carregarCategoria();
}
