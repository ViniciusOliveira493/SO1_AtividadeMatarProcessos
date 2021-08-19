package view;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.KillController;

public class Main {
	public static void main(String[] args) {
		KillController killControler = new KillController();
		
		int escolha = 0;
		while (escolha!=9) {
			try {
				escolha = Integer.parseInt(
						JOptionPane.showInputDialog("Digite 1 para listar os processos \n"+
														"Digite 2 para matar um processo com PID \n"+
														"Digite 3 para listar matar um processo com o Nome \n"+
														"Digite 9 para encerrar"));	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Valor inválido !");
				escolha = 0;
			}
			
			switch (escolha) {
			case 1:
				JTextArea text = new JTextArea(killControler.listarProcessos());
				JScrollPane pane = new JScrollPane(text);
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				pane.setPreferredSize(new Dimension(700,500));
				JOptionPane.showMessageDialog(null, pane);
				break;
			case 2:
				try {
					int n = Integer.parseInt(JOptionPane.showInputDialog("digite o PID do processo"));	
					killControler.mataPid(n);
					JOptionPane.showMessageDialog(null, "Processo encerrado !");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Valor inválido !");
				}
						
				break;
			case 3:
				String processo = JOptionPane.showInputDialog("digite o nome do processo");	
				killControler.mataNome(processo);
				JOptionPane.showMessageDialog(null, "Processo encerrado !");
				break;
			case 9:
				
				break;

			default:
				break;
			}
			
		}
	}
}
