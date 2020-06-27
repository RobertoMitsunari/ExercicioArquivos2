package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		IArquivosController controlArq = new ArquivosController();
		String arquivo = "dados";
		
		try {
			int opc = 0;
			controlArq.verificaDirTemp();
			do{
				opc = Integer.parseInt(JOptionPane.showInputDialog("1 - verificaRegistro \n2 - imprimeCadastro \n3 -  insereCadastro"
						+ "\n9 - sair"));
				switch(opc) {
					case 1:
						System.out.println(controlArq.verificaRegistro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do registro"))));
						break;
					case 2:
						controlArq.imprimeCadastro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do registro")));
						break;
					case 3:
						controlArq.insereCadastro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do registro")),
								JOptionPane.showInputDialog("Digite nome do registro"),
								JOptionPane.showInputDialog("Digite um email para o registro"));
						break;
					default:
						break;
				}
					
			}while(opc != 9);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
