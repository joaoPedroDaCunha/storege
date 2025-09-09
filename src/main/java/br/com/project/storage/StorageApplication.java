package br.com.project.storage;

import javax.swing.SwingUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import br.com.project.storage.front.gui.Gui;

@SpringBootApplication
public class StorageApplication {

    public static void main(String[] args) {
        // Precisamos que o AWT seja head-full
        System.setProperty("java.awt.headless", "false");

        // Cria o SpringApplication e força headless = false
        SpringApplication app = new SpringApplication(StorageApplication.class);
        app.setHeadless(false);

        // Roda o contexto UMA vez e guarda o ApplicationContext
        ConfigurableApplicationContext ctx = app.run(args);

        // Dentro do EDT do Swing, pega o bean Gui e exibe a janela
        SwingUtilities.invokeLater(() -> {
            try {
                Gui gui = ctx.getBean(Gui.class);
                gui.setVisible(true);
            } catch (Exception e) {
                
            }
        });
    }
}