package com.br.projetoIntegrador.cc_2015;

import java.util.Random;

public class App {
	
	public static RBTree rBTree = new RBTree();
	
	public static void main(String[] args) {
		int count = 0;
		Random generateCPF = new Random();
		
		for(int i = 0; i < (10 + count); i++) {
			Dado dado = new Dado();
			dado.setCpf(String.valueOf(4000+(generateCPF.nextInt(11))));
			
			NoRBT no = rBTree.search(dado);
			if(no != null) {
				count++;
			} else {
				rBTree.insert(dado);
				rBTree.prefixo();
				System.out.println();
			}
		}
		System.exit(0);
	}
	
}
