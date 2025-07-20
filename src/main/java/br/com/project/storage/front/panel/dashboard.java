package br.com.project.storage.front.panel;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class dashboard extends JPanel{

    public dashboard(){
        setLayout(new FlowLayout());

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(20);
        JButton botaoSalvar = new JButton("Salvar");

        add(labelNome);
        add(campoNome);
        add(botaoSalvar);
    }
    
}
