package br.com.project.storage.front.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.controller.ProductEntreyController;
import br.com.project.storage.front.panel.CadasterProductPanel;
import br.com.project.storage.front.panel.EntryPanelView;
import br.com.project.storage.front.panel.ProductEntryPanel;
import br.com.project.storage.front.panel.ProductItemView;
import br.com.project.storage.front.panel.dashboard;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@Component
public class Gui extends JFrame implements WindowListener{

    private @Autowired ProductController productController;
    private @Autowired ProductEntreyController productEntreyController;


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
        JMenuBar menuBar = new JMenuBar();
        JMenu menuEntry = new JMenu("Entrada");
        JMenuItem itemNovo = new JMenuItem("Nova Entrada");
        itemNovo.addActionListener(e ->{
            getContentPane().removeAll();
            ProductEntryPanel productEntryPanel = new ProductEntryPanel(productEntreyController, productController) ;
            getContentPane().add(productEntryPanel);
            revalidate();
            repaint();
        });
        JMenuItem itemAbrir = new JMenuItem("Historico");
        itemAbrir.addActionListener(e ->{
            getContentPane().removeAll();
            EntryPanelView entryPanelView = new EntryPanelView(productEntreyController,productController);
            getContentPane().add(entryPanelView);
            revalidate();
            repaint();
        });

        menuEntry.add(itemNovo);
        menuEntry.add(itemAbrir);
        menuBar.add(menuEntry);

        JMenu menConferecia = new JMenu("Conferencia");
        JMenuItem itemSobre = new JMenuItem("Entradas Para Conferir");
        menConferecia.add(itemSobre);
        menuBar.add(menConferecia);

        JMenu menuProdct = new JMenu("Produto");
        JMenuItem ItemProdutc = new JMenuItem("Cadastrar produto");
        ItemProdutc.addActionListener(e->{
            getContentPane().removeAll();
            CadasterProductPanel CadasterProductPanel = new CadasterProductPanel(productController);
            getContentPane().add(CadasterProductPanel);
            revalidate();
            repaint();
        });
        JMenuItem ItemProdutcView = new JMenuItem("Produtos cadastrados");
        ItemProdutcView.addActionListener(e->{
            getContentPane().removeAll();
            ProductItemView ProductItemView = new ProductItemView(productController);
            getContentPane().add(ProductItemView);
            revalidate();
            repaint();
        });
        menuProdct.add(ItemProdutc);
        menuProdct.add(ItemProdutcView);
        menuBar.add(menuProdct);

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
