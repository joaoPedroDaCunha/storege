package br.com.project.storage.front.Dialog;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.enums.CountingFormat;
import br.com.project.storage.back.model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductFormDialog extends JDialog {

    private final ProductController controller;
    private final Product product;

    private JTextField codeFornField;
    private JTextField nameField;
    private JTextField weightField;
    private JTextField unitField;
    private JComboBox<CountingFormat> formatCombo;

    public ProductFormDialog(Window owner, String title, ProductController controller, Product product) {
        super(owner, title);
        this.controller = controller;
        this.product = product;
        initComponents();
        populateFields();
        pack();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 1: Código Fornecedor
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Código Forn."), gbc);
        gbc.gridx = 1;
        codeFornField = new JTextField(20);
        formPanel.add(codeFornField, gbc);

        // Linha 2: Nome do Produto
        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Nome do Produto"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Linha 3: Peso Base
        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Peso Base"), gbc);
        gbc.gridx = 1;
        weightField = new JTextField(20);
        formPanel.add(weightField, gbc);

        // Linha 4: Formato
        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Formato"), gbc);
        gbc.gridx = 1;
        formatCombo = new JComboBox<>(CountingFormat.values());
        formPanel.add(formatCombo, gbc);

        // Linha 5: Unidade Base
        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Unidade Base"), gbc);
        gbc.gridx = 1;
        unitField = new JTextField(20);
        formPanel.add(unitField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        JButton saveButton   = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(e -> onSave());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void populateFields() {
        if (product != null) {
            codeFornField.setText(String.valueOf(product.getCodeProductForn()));
            nameField.setText(product.getNameProduct());
            weightField.setText(String.valueOf(product.getBaseWeight()));
            formatCombo.setSelectedItem(product.getFormat());
            unitField.setText(String.valueOf(product.getBaseUnit()));
        }
    }

    private void onSave() {
        try {
            product.setCodeProductForn(Integer.parseInt(codeFornField.getText().trim()));
            product.setNameProduct(nameField.getText().trim());
            product.setBaseWeight(Double.parseDouble(weightField.getText().trim()));
            product.setFormat((CountingFormat) formatCombo.getSelectedItem());
            product.setBaseUnit(Integer.parseInt(unitField.getText().trim()));

            // Use o método apropriado do controller para salvar ou atualizar
            controller.put(product);

            dispose();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                "Peso Base deve ser um número válido.",
                "Erro de validação",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar produto:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}