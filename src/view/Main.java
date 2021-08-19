package view;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.KillController;

public class Main {
	public static void main(String[] args) {
		KillController killControler = new KillController();
		String mensagemDeErro = "Algo deu errado";
		int escolha = 0;
		while (escolha!=9) {
			try {
				escolha = Integer.parseInt(
						JOptionPane.showInputDialog("Digite 1 para listar os processos \n"+
														"Digite 2 para matar um processo com PID \n"+
														"Digite 3 para listar matar um processo com o Nome \n"+
														"Digite 9 para encerrar"));	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Valor inv�lido !");
				escolha = 0;
			}
			
			switch (escolha) {
			case 1:
				JTextArea text = new JTextArea(killControler.listarProcessos());
				JScrollPane pane = new JScrollPane(text);
				text.setLineWrap(false);
				text.setWrapStyleWord(false);
				pane.setPreferredSize(new Dimension(800,500));
				JOptionPane.showMessageDialog(null, pane,"Processos",JOptionPane.OK_OPTION);
				break;
			case 2:
				try {
					int n = Integer.parseInt(JOptionPane.showInputDialog("digite o PID do processo"));	
					if(killControler.mataPid(n)) {
						JOptionPane.showMessageDialog(null, "Processo encerrado !");
					}else {
						System.out.println(mensagemDeErro);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Valor inv�lido !");
				}
						
				break;
			case 3:
				String processo = JOptionPane.showInputDialog("digite o nome do processo");	
				if(killControler.mataNome(processo)) {
					JOptionPane.showMessageDialog(null, "Processo encerrado !");
				}else {
					System.out.println(mensagemDeErro);
				}
				break;
			case 9:
				
				break;

			default:
				JOptionPane.showMessageDialog(null, "Valor inv�lido !");
				break;
			}
			
		}
	}
}
