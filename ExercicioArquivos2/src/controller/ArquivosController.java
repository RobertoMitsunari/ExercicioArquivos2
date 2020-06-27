package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController {

	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File("C:\\TEMP");
		if (!dir.exists()) {
			dir.mkdir();
		}

	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File("C:\\TEMP", arquivo + ".csv");
		if (arq.exists()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String[] texto;
			while (linha != null) {
				linha = linha.replace("	", ";");
				texto = linha.split(";");
				if (texto[0].equals(Integer.toString(codigo))) {
					buffer.close();
					leitor.close();
					fluxo.close();
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			return false;
		} else {
			throw new IOException("Arquivo Inválido");
		}
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		if (this.verificaRegistro(arquivo, codigo)) {
			File arq = new File("C:\\TEMP", arquivo + ".csv");
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String[] texto;
			while (linha != null) {
				linha = linha.replace("	", ";");
				texto = linha.split(";");
				if (texto[0].equals(Integer.toString(codigo))) {
					System.out
							.println("Codigo: " + texto[0] + "\n" + "Nome: " + texto[1] + "\n" + "Email: " + texto[2]);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			System.out.println("Arquivo não encontrado");
		}

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		if (!(this.verificaRegistro(arquivo, codigo))) {
			File arq = new File("C:\\TEMP", arquivo + ".csv");
			FileWriter fileWriter = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write("\n" + Integer.toString(codigo) + ";" + nome + ";" + email);
			print.flush();
			print.close();
			fileWriter.close();
		}

	}

}
