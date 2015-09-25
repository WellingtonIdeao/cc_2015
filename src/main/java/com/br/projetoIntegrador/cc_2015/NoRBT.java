package com.br.projetoIntegrador.cc_2015;

public class NoRBT {
	
	private IDado dado;
	private NoRBT noEsq;
	private NoRBT noDir;
	private NoRBT noRoot;
	private boolean cor; //False = red  True = black
	
	public NoRBT(IDado dado) {
		this.dado = dado;
		this.noEsq = noEsq;
		this.noDir = noDir;
		this.noRoot = noRoot;
	}
	
	public NoRBT(NoRBT pai, IDado dado, boolean cor) {
		this.dado = dado;
		this.noEsq = noEsq;
		this.noDir = noDir;
		this.noRoot = noRoot;
		this.cor = cor;
	}
	
	public NoRBT getAvo() {
		if((this.noRoot != null) && (this.noRoot.noRoot != null)) {
			return this.noRoot.noRoot;
		}
		return null;
	}
	
	public NoRBT getIrmao() {
		if(this.noRoot != null) {
			if(this == this.noRoot.noEsq) {
				return this.noRoot.noDir;
			}
			else {
				return this.noRoot.noEsq;
			}
		}
		return null;
	}
	
	public NoRBT getTio() {
		if((this.noRoot != null) && (this.noRoot.noRoot != null)) {
			return noRoot.getIrmao();
		}
		return null;		
	}
	
	//Atualiza cpf
	public void attCpf(IDado dado) {
		this.dado = dado;
	}
	
	
	public void exibirNos() {
		if(this.noEsq != null) {
			this.noEsq.exibirNos();
		}
		StringBuffer dado = new StringBuffer();
		dado.append("CPF: ");
		dado.append(toString());
		dado.append("\tCor: ");
		dado.append(exibirCor());
		System.out.println(dado);
		
		if(this.noDir != null) {
			this.noDir.exibirNos();
		}
	}
	
	public void prefixo(NoRBT no) {
		if(no != null) {
			System.out.println(no.dado + " " + no.exibirCor() + " | ");
			prefixo(no.noEsq);
			prefixo(no.noDir);
		}
	}
	
	public void posfixo(NoRBT no) {
		if(no != null) {			
			prefixo(no.noEsq);
			prefixo(no.noDir);
			System.out.println(no.dado + " " + no.exibirCor() + " | ");
		}
	}
	
	public void emOrdem(NoRBT no) {
		if(no != null) {
			prefixo(no.noEsq);
			System.out.println(no.dado + " " + no.exibirCor() + " | ");
			prefixo(no.noDir);
		}
	}
	
//	public boolean equals(String dado) {
//	    	return noRaiz.dado.equals(dado);
//	}
	
//	public String toString() {
//        return dado.toString();
//	}
	
	public String exibirCor() {
		if(!this.cor) {
			return "RED";
		}
		else {
			return "BLACK";
		}
	}
	
	public IDado getIDado() {
		return dado;
	}

	public void setIDado(IDado dado) {
		this.dado = dado;
	}

	public NoRBT getNoEsq() {
		return noEsq;
	}

	public void setNoEsq(NoRBT noEsq) {
		this.noEsq = noEsq;
	}

	public NoRBT getNoDir() {
		return noDir;
	}

	public void setNoDir(NoRBT noDir) {
		this.noDir = noDir;
	}

	public NoRBT getNoRoot() {
		return noRoot;
	}

	public void setNoRoot(NoRBT noRoot) {
		this.noRoot = noRoot;
	}

	public boolean isCor() {
		return cor;
	}

	public void setCor(boolean cor) {
		this.cor = cor;
	}
	
	

}
