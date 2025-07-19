package br.com.project.storage;

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
            } catch (Exception e) {
                System.out.println("Erro ao iniciar a GUI: " + e.getMessage());e.printStackTrace();
            }
        });
	}

}
