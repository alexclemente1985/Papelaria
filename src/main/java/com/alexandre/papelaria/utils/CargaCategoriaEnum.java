package com.alexandre.papelaria.utils;

public enum CargaCategoriaEnum {
	INFO("informatica", 1), ESCOLAR("material_escolar", 2), ARTESANATO("artesanato", 3), DECORACAO("decoracao", 4),
	LIVROS("livros", 5), ESCRITORIO("material_escritorio", 6);

	private String descricao;
	private Integer ordinal;
	
	
	
	private CargaCategoriaEnum(String name, Integer ordinal) {
		this.descricao = name;
		this.ordinal = ordinal;

	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

}
