package br.com.project.storage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.project.storage.front.gui.Gui;

@SpringBootApplication
public class StorageApplication {

	public static void main(String[] args) {
		
		System.setProperty("java.awt.headless", "false");

		SpringApplication.run(StorageApplication.class, args);
			new SpringApplicationBuilder(StorageApplication.class)
            .headless(false).run(args);

		 SwingUtilities.invokeLater(() -> {
            try {
                Gui gui = new Gui();
                gui.setTitle("Minha GUI");
                gui.setSize(800, 600);
                gui.setLocationRelativeTo(null); // Centraliza na tela
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true); // Exibe a janela
            } catch (Exception e) {
                System.out.println("Erro ao iniciar a GUI: " + e.getMessage());
    			e.printStackTrace();
            }
        });
	}

}
