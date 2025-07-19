package br.com.project.storage.front.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.project.storage.front.panel.dashboard;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Gui extends JFrame implements WindowListener{

    public Gui(){
        if (GraphicsEnvironment.isHeadless()) {
                System.err.println("Ambiente headless detectado. Operações gráficas não são suportadas."); 
                throw new HeadlessException("Ambiente headless detectado."); 
            } 
            System.out.println("Construtor Gui chamado");  
            try {
                initComponents();
            } 
            catch (Exception e) { 
                System.out.println("Erro em initComponents: " + e.getMessage());
                e.printStackTrace(); 
            } 
    }

    private void initComponents(){

        setTitle("Minha GUI");
        setSize(800, 600);
        setLocationRelativeTo(null);
		dashboard dashboard = new dashboard();
		setLayout(new BorderLayout());
		add(dashboard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
         // Criando barra de menu
        JMenuBar menuBar = new JMenuBar();

        // Menu principal "Arquivo"
        JMenu menuArquivo = new JMenu("Arquivo");

        // Itens do menu
        JMenuItem itemNovo = new JMenuItem("Novo");
        JMenuItem itemAbrir = new JMenuItem("Abrir");
        JMenuItem itemSair = new JMenuItem("Sair");

        // Adicionando itens ao menu
        menuArquivo.add(itemNovo);
        menuArquivo.add(itemAbrir);
        menuArquivo.addSeparator(); // linha separadora
        menuArquivo.add(itemSair);

        // Adicionando o menu à barra
        menuBar.add(menuArquivo);

        // Menu secundário "Ajuda"
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        menuAjuda.add(itemSobre);
        menuBar.add(menuAjuda);

        // Define a barra de menu na janela
        setJMenuBar(menuBar);
    
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowClosed'");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }
    
}
