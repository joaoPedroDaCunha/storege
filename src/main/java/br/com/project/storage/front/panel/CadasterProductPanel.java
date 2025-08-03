package br.com.project.storage.front.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import br.com.project.storage.back.enums.CountingFormat;
import br.com.project.storage.back.model.Product;

public class CadasterProductPanel extends JPanel {

    private JTextField txtCodeForn;
    private JTextField txtNameProduct;
    private JTextField txtBaseWeight;
    private JComboBox<CountingFormat> cmbFormat;
    private JTextField txtBaseUnit;

    public CadasterProductPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Espaçamento entre os componentes
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo: Código do Fornecedor
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Código do Fornecedor:"), gbc);
        txtCodeForn = new JTextField(20);
        gbc.gridx = 1;
        add(txtCodeForn, gbc);

        // Campo: Nome do Produto
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nome do Produto:"), gbc);
        txtNameProduct = new JTextField(20);
        gbc.gridx = 1;
        add(txtNameProduct, gbc);

        // Campo: Peso Base
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Peso Base:"), gbc);
        txtBaseWeight = new JTextField(20);
        gbc.gridx = 1;
        add(txtBaseWeight, gbc);

        // Campo: Formato de Contagem
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Formato de Contagem:"), gbc);
        cmbFormat = new JComboBox<>(CountingFormat.values());
        gbc.gridx = 1;
        add(cmbFormat, gbc);

        // Campo: Unidade Base
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Unidade Base:"), gbc);
        txtBaseUnit = new JTextField(20);
        gbc.gridx = 1;
        add(txtBaseUnit, gbc);

        // Botão: Salvar
        JButton btnSalvar = new JButton("Cadastrar Produto");
        btnSalvar.addActionListener((ActionEvent e) -> {
            try {
                int codeForn = Integer.parseInt(txtCodeForn.getText());
                String name = txtNameProduct.getText();
                double weight = Double.parseDouble(txtBaseWeight.getText());
                CountingFormat format = (CountingFormat) cmbFormat.getSelectedItem();
                int unit = Integer.parseInt(txtBaseUnit.getText());

                Product product = new Product(0, codeForn, name, weight, format, unit);
                JOptionPane.showMessageDialog(this, "Produto cadastrado:\n" + product.getNameProduct());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSalvar, gbc);
    }
}