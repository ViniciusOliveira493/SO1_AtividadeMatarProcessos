package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	private String getOs() {
		return System.getProperty("os.name");
	}
	
	private String readProcess(String process) {
		StringBuffer strBuffer = new StringBuffer();
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(reader);
			String linha = buffer.readLine();
			while (linha != null) {
				strBuffer.append(linha + "\n");
				linha = buffer.readLine();				
			}			
			buffer.close();
			reader.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
	}
	
	public String listarProcessos() {
		StringBuffer resposta = new StringBuffer();
		if(getOs().contains("Windows")) {
			resposta.append(readProcess("tasklist /fo table"));
		}else{
			resposta.append(readProcess("ps -ef"));
		}
		System.out.println(resposta.toString());
		return resposta.toString();
	}
	
	public boolean mataPid(int pid) {
		boolean resultado = false;
		if(getOs().contains("Windows")) {
			try {
				Runtime.getRuntime().exec("taskkill /PID " + pid);
				resultado = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Runtime.getRuntime().exec("kill -9 " + pid);
				resultado = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
		
	}
	
	public boolean mataNome(String nomeProcesso) {
		boolean resultado = false;
		if(getOs().contains("Windows")) {
			try {
				Runtime.getRuntime().exec("taskkill /IM " + nomeProcesso);
				resultado = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Runtime.getRuntime().exec("killall " + nomeProcesso);
				resultado = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
		
	}
}
