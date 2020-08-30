package com.fellows.cursospring.domain.enums;

public enum TipoCliente {

	PESSOAFISICA( 1, "Pessoa Física" ), PESSOAJURIDICA( 2, "Pessoa Jurídica" );

	private int		cod;
	private String	desc;

	private TipoCliente( int cod, String desc ) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static TipoCliente toEnum( Integer cod ) {
		if ( cod == null ) {
			return null;
		}

		for ( TipoCliente tipo : TipoCliente.values() ) {
			if ( cod.equals( tipo.getCod() ) ) {
				return tipo;
			}
		}

		throw new IllegalArgumentException( "Id inválido: " + cod );
	}

}
