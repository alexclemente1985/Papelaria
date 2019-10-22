package com.alexandre.papelaria.services.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.papelaria.dao.CategoriaDAO;
import com.alexandre.papelaria.models.Categoria;
import com.alexandre.papelaria.services.CategoriaService;
import com.alexandre.papelaria.utils.CargaCategoriaEnum;

@Service("CategoriaService")
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAO dao;

	@Override
	@Transactional
	public List<Categoria> listarCategorias() {
		List<Categoria> catList = new ArrayList<Categoria>();
		dao.listarCategorias().forEach(e -> catList.add(e));
		return catList;
	}

	@Override
	@Transactional
	public synchronized void carregarCategoria() {

		List<Categoria> catList = new ArrayList<Categoria>();
		dao.listarCategorias().forEach(e -> catList.add(e));

		List<CargaCategoriaEnum> cargaEnum = new ArrayList<CargaCategoriaEnum>(EnumSet.allOf(CargaCategoriaEnum.class));

		if (catList.size() == 0) {
			for (int i = 0; i < cargaEnum.size(); i++) {
				Categoria c = new Categoria(cargaEnum.get(i).getOrdinal(), cargaEnum.get(i).getDescricao());
				dao.cadastrarCategoria(c);
			}
		}

		

	}

}
