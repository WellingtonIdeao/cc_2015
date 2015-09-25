package com.br.projetoIntegrador.cc_2015;

public class RBTree {
	
	private NoRBT rootRBT;
	private static boolean RED = false;
	private static boolean BLACK = true;
	
	//Construtor default
	public RBTree() {
		rootRBT = null;
	}
	
	public RBTree(NoRBT root) {
		rootRBT = root;
	}
	
	//Exibir árvore
	public void exibirTree() {
		if(this.rootRBT != null) {
			this.rootRBT.exibirNos();
		}
	}
	
	//Exibir prefixo
	public void prefixo() {
		if(this.rootRBT != null) {
			this.rootRBT.prefixo(rootRBT);
		}
	}
	
	//Exibir posfixo
	public void posfixo() {
		if(this.rootRBT != null) {
			this.rootRBT.posfixo(rootRBT);
		}
	}
	
	//Exibir em Ordem
	public void emOrdem() {
		if(this.rootRBT != null) {
			this.rootRBT.emOrdem(rootRBT);
		}
	}
	
	private void trocaElemento(NoRBT a, NoRBT b) {
		//se A é raiz
		if(a.getNoRoot() == null) {
			rootRBT = b;
		}
		
		//Verifica em que lado esta o pai de A
		//Pai de A passa a apontar para B
		else if(a == a.getNoRoot().getNoEsq()) {
			a.getNoRoot().setNoEsq(b);
		}
		
		else {
			a.getNoRoot().setNoDir(b);
		}
		
		if(b != null) {
			b.setNoRoot(a.getNoRoot());
		}			
	}
	
	public void insert(IDado dado) {
		if(rootRBT == null) {
			rootRBT = new NoRBT(dado);
			correctTreeInsert(rootRBT);
		}
		else {
			insertNo(rootRBT, dado);
		}
	}
	
	private void insertNo(NoRBT NoRBT, IDado dado) {
		if(!dado.equals(NoRBT.getIDado())) {
			if(dado.comparteTo(NoRBT.getIDado()) < 0) {
				if(NoRBT.getNoEsq() == null) {
					NoRBT.setNoEsq(new NoRBT(NoRBT, dado, RED));
					correctTreeInsert(NoRBT.getNoEsq());
				}
				else {
					if(NoRBT.getNoDir() == null) {
						NoRBT.setNoDir(new NoRBT(NoRBT, dado, RED));
						correctTreeInsert(NoRBT.getNoDir());
					}
					else {
						insertNo(NoRBT.getNoDir(), dado);
					}
				}
			}
		}
	}
	
	public void remove(Dado dado) {
		removeNo(rootRBT, dado);
	}
	
	private void removeNo(NoRBT NoRBT, Dado dado) {
		//Não encontra o nó
		if(NoRBT == null) {
			return;
		}
		
		else if(dado.equals(NoRBT.getIDado())) {
			
			//Verifica se tem filho esquerdo
			if(NoRBT.getNoEsq() == null) {
				trocaElemento(NoRBT, NoRBT.getNoDir());
				
				if(NoRBT.getNoRoot() != null) {
					correctTreeInsert(NoRBT);
				}
			}
			
			else {
				NoRBT minNoRBT = NoRBT.getNoDir();
				
				while(minNoRBT.getNoEsq() == null) {
					minNoRBT = minNoRBT.getNoEsq();
				}
				
				if(minNoRBT.getNoRoot() != NoRBT) { 
					trocaElemento(minNoRBT, minNoRBT.getNoDir());
					
					minNoRBT.setNoDir(NoRBT.getNoDir());
					minNoRBT.getNoDir().setNoRoot(minNoRBT);
				}
				
				trocaElemento(NoRBT, minNoRBT);
				minNoRBT.setNoEsq(NoRBT.getNoEsq());
				minNoRBT.getNoDir().setNoRoot(minNoRBT);
				correctTreeInsert(minNoRBT);
			}					
		}
		
		else if(dado.comparteTo(NoRBT.getClass()) < 0) {
			removeNo(NoRBT.getNoEsq(), dado);
		}
	
		else {
			removeNo(NoRBT.getNoDir(), dado);
		}
		
	}
	
	public NoRBT search(Dado dado) {
		return searchElement(rootRBT, dado);
	}
	
	public boolean alter(Dado dado) {
		NoRBT aux = search(dado);
		
		if(aux != null) {
			aux.setIDado(dado);
		}
		
		return false;
	}
	
	private NoRBT searchElement(NoRBT no, Dado dado) {
		if(no == null) {
			return null;
		}
		
		else {
			if(dado.equals(no.getIDado())) {
				return no;
			}
			
			else if(dado.comparteTo(no.getIDado()) < 0) {
				return searchElement(no.getNoEsq(), dado);
			}
			else {
				return searchElement(no.getNoDir(), dado);
			}
		}
	}
	
	private void leftRotation(NoRBT NoRBT) {
		NoRBT aux = NoRBT.getNoDir();
		trocaElemento(NoRBT, aux);
		NoRBT.setNoDir(aux.getNoEsq());
		
		if(aux.getNoEsq() != null) {
			aux.getNoEsq().setNoRoot(NoRBT);
		}
		
		aux.setNoEsq(NoRBT);
		NoRBT.setNoRoot(aux);
	}
	
	private void rightRotation(NoRBT NoRBT) {
		NoRBT aux = NoRBT.getNoEsq();
		trocaElemento(NoRBT, aux);
		NoRBT.setNoEsq(aux.getNoDir());
		
		if(aux.getNoDir() != null) {
			aux.getNoDir().setNoRoot(NoRBT);
		}
		
		aux.setNoDir(NoRBT);
		NoRBT.setNoRoot(aux);
	}
	
	private void correctTreeInsert(NoRBT no) {
		//Se for a raiz, muda a cor para preto
		if(no == rootRBT) {
			no.setCor(BLACK);			
		}
		
		//Se o pai for preto, não muda
		else if(no.getNoRoot().isCor() == BLACK) {
			return;
		}
		
		else if(no.getNoRoot().isCor() == RED) {
			if(no.getTio() != null && no.getTio().isCor() == RED) {
				no.getNoRoot().setCor(BLACK);
				no.getTio().setCor(BLACK);
				no.getAvo().setCor(RED);
				correctTreeInsert(no.getAvo());			
			}
			
			else {
				if(no == no.getNoRoot().getNoDir() && no.getNoRoot() == no.getAvo().getNoEsq()) {
					leftRotation(no.getNoEsq());
					no = no.getNoEsq();
				}
				
				else if(no == no.getNoRoot().getNoEsq() && no.getNoRoot() == no.getAvo().getNoDir()) {
					rightRotation(no.getNoRoot());
					no = no.getNoDir();
				}
				
				no.getNoRoot().setCor(BLACK);
				if(no.getAvo() != null) {
					no.getAvo().setCor(RED);
				}
				
				if(no == no.getNoRoot().getNoEsq() && no.getNoRoot() == no.getAvo().getNoEsq())
					rightRotation(no.getAvo());
				
				else if(no == no.getNoRoot().getNoDir() && no.getNoRoot() == no.getAvo().getNoDir())
					leftRotation(no.getAvo());
			}
						
		}
		
		if(rootRBT.isCor() != BLACK) {
			correctTreeInsert(no.getNoRoot());
		}			
	}

	public NoRBT getRootRBT() {
		return rootRBT;
	}

	public void setRootRBT(NoRBT rootRBT) {
		this.rootRBT = rootRBT;
	}

	public static boolean isRED() {
		return RED;
	}

	public static void setRED(boolean rED) {
		RED = rED;
	}

	public static boolean isBLACK() {
		return BLACK;
	}

	public static void setBLACK(boolean bLACK) {
		BLACK = bLACK;
	}

}
