package br.com.project.storage.front.Dialog;

import br.com.project.storage.back.model.ProductAssistant;

import javax.swing.*;
import java.awt.*;

public class ItemDialog extends JDialog {
    private ProductAssistant result;

    public ItemDialog(Window owner) {
        super(owner, "Novo Item", ModalityType.APPLICATION_MODAL);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtBatch = new JTextField(10);
        JTextField txtQty   = new JTextField(10);
        JTextField txtWgt   = new JTextField(10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Código do Lote:"), gbc);
        gbc.gridx = 1;
        add(txtBatch, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Quantidade:"), gbc);
        gbc.gridx = 1;
        add(txtQty, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Peso do Lote:"), gbc);
        gbc.gridx = 1;
        add(txtWgt, gbc);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> {
            try {
                int batch = Integer.parseInt(txtBatch.getText());
                int qty   = Integer.parseInt(txtQty.getText());
                double wgt   = Double.parseDouble(txtWgt.getText());
                result = new ProductAssistant(batch, qty, wgt);
                dispose();
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Valores inválidos:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnOk, gbc);

        pack();
        setLocationRelativeTo(owner);
    }

    public ProductAssistant getResult() {
        return result;
    }
}