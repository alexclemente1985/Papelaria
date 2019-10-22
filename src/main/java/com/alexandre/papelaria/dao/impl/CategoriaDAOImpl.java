package com.alexandre.papelaria.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alexandre.papelaria.dao.CategoriaDAO;
import com.alexandre.papelaria.models.Categoria;

@Repository("CategoriaDAO")
public class CategoriaDAOImpl implements CategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listarCategorias() {
		Session session = sessionFactory.getCurrentSession();
		List<Categoria> categorias = session.createQuery("from Categoria").list();
		return categorias;
	}

	@Override
	public void cadastrarCategoria(Categoria c) {
		Session session = sessionFactory.getCurrentSession();
		session.save(c);

	}

	@Override
	public Categoria encontrarCategoria(int id) {
		Session session = sessionFactory.getCurrentSession();
		Categoria c = session.get(Categoria.class, id);
		return c;
	}

}
