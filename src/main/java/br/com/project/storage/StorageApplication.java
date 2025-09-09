package br.com.project.storage;

import javax.swing.SwingUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import br.com.project.storage.front.gui.Gui;

@SpringBootApplication
public class StorageApplication {

    public static void main(String[] args) {
        
        System.setProperty("java.awt.headless", "false");
        
        SpringApplication app = new SpringApplication(StorageApplication.class);
        app.setHeadless(false);

        ConfigurableApplicationContext ctx = app.run(args);

        SwingUtilities.invokeLater(() -> {
            try {
                Gui gui = ctx.getBean(Gui.class);
                gui.setVisible(true);
            } catch (Exception e) {
                
            }
        });
    }
}