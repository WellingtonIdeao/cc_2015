package com.br.projetoIntegrador.cc_2015;

public class Dado implements IDado {
	
	private String cpf = "";
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean equals(Object dado) {
		return this.cpf.equals(((Dado) dado).getCpf());
		
	}

	public int comparteTo(Object dado) {
		return this.cpf.compareTo(((Dado) dado).getCpf());
	}
	
	public String toString() {
		return cpf;
	}
}
