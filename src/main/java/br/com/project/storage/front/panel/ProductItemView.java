package br.com.project.storage.front.panel;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.model.Product;
import br.com.project.storage.front.Dialog.ProductFormDialog;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductItemView extends JPanel {

    private final ProductController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Product> products = new ArrayList<>();

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

        // Modelo somente leitura
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

        // Painel de botões embaixo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        JButton editButton   = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");

        // Ações dos botões
        editButton.addActionListener(e -> editSelectedProduct());
        deleteButton.addActionListener(e -> deleteSelectedProduct());

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        // Limpa lista e linhas antigas
        products.clear();
        tableModel.setRowCount(0);

        try {
            // Carrega todos os produtos
            products.addAll(controller.getAll());

            // Preenche a tabela
            for (Product p : products) {
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

    private void editSelectedProduct() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Selecione um produto para editar.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = table.convertRowIndexToModel(viewRow);
        Product selected = products.get(modelRow);

        ProductFormDialog dialog = new ProductFormDialog(
            SwingUtilities.getWindowAncestor(this),
            "Editar Produto",
            controller,
            selected
        );
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);
        dialog.setVisible(true);

        loadData();
    }

    private void deleteSelectedProduct() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Selecione um produto para excluir.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja excluir o produto selecionado?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int modelRow = table.convertRowIndexToModel(viewRow);
        Product selected = products.get(modelRow);

        try {
            controller.deleteById(selected.getCodeProduct());           // ou deleteById(selected.getId());
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao excluir produto:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}