package br.com.project.storage.front.panel;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.model.Product;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Component
public class ProductItemView extends JPanel {

    private final ProductController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public ProductItemView(ProductController controller) {
        this.controller = controller;
        initUI();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout(16, 16));

        // Cabeçalhos da tabela
        String[] columns = {
            "Código Forn.",
            "Nome do Produto",
            "Peso Base",
            "Formato",
            "Unidade Base"
        };

        // Modelo (somente leitura)
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);

        // Scroll pane centralizado
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        // Limpa linhas antigas
        tableModel.setRowCount(0);

        try {
            // Obtém todos os produtos
            // Altere para List<Product> se seu controller retornar lista em vez de Set
            for (Product p : controller.getAll()) {
                Object[] row = {
                    p.getCodeProductForn(),
                    p.getNameProduct(),
                    p.getBaseWeight(),
                    p.getFormat(),
                    p.getBaseUnit()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produtos:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}