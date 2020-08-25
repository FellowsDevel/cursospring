package com.fellows.cursospring.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int cod;
	private String desc;

	private EstadoPagamento(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento tipo : EstadoPagamento.values()) {
			if (cod.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
