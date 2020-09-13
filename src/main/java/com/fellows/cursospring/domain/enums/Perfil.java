package com.fellows.cursospring.domain.enums;

public enum Perfil {

	ADMIN( 1, "ROLE_ADMIN" ),
	CLIENTE( 2, "ROLE_CLIENTE" );

	private int		cod;
	private String	desc;

	private Perfil( int cod, String desc ) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static Perfil toEnum( Integer cod ) {
		if ( cod == null ) {
			return null;
		}

		for ( Perfil tipo : Perfil.values() ) {
			if ( cod.equals( tipo.getCod() ) ) {
				return tipo;
			}
		}

		throw new IllegalArgumentException( "Id inválido: " + cod );
	}

}
