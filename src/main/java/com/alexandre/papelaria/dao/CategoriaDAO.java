package com.alexandre.papelaria.dao;

import java.util.List;

import com.alexandre.papelaria.models.Categoria;

public interface CategoriaDAO {
	public List<Categoria> listarCategorias();

	public void cadastrarCategoria(Categoria c);
	
	public Categoria encontrarCategoria(int id); 
}
