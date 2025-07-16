package br.com.project.storage.front.gui;

import javax.swing.JFrame;

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
