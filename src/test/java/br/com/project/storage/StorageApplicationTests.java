package br.com.project.storage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.enums.CountingFormat;
import br.com.project.storage.back.model.Product;

@SpringBootTest
class StorageApplicationTests {
	private @Autowired ProductController Controller ;


	@Test
    void contextLoads() {
        // Apenas verifica injeção
        assertNotNull(Controller);

        // Se for testar o post, crie um objeto válido
        Product product = new Product(
            123,                       // código do fornecedor
            "Produto Teste",           // nome
            1.5,                       // peso
            CountingFormat.Unit,       // formato
            10                         // unidade
        );

        Controller.post(product);

        // Verifique que o produto foi salvo (por exemplo, via getByName/getAll)
        assertTrue(Controller.getAll().contains(product));
		Controller.deleteById(product.getCodeProduct());
    }


}
